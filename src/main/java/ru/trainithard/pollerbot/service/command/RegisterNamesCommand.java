package ru.trainithard.pollerbot.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import ru.trainithard.pollerbot.service.dto.UserMessage;
import ru.trainithard.pollerbot.service.validator.UserNamesValidator;

import static ru.trainithard.pollerbot.service.command.CommandName.REGISTER_NAMES;

@Component
@RequiredArgsConstructor
public class RegisterNamesCommand extends AbstractCommand {
    private final UserNamesValidator validator;
    private final RegisterEmailCommand registerEmailCommand;

    @Override
    public BotApiMethodMessage execute(UserMessage userMessage) {
        if (isFirstInvocation(userMessage)) {
            saveUserSession(shiftSessionToThisCommand(userMessage));
            return getTextMessage(userMessage);
        }

        if (!validator.validate(userMessage.getMessage())) {
            return getErrorMessage(userMessage);
        } else {
            getSession(userMessage).setNextCommandName(CommandName.REGISTER_EMAIL);
            saveUserSession(userMessage);
            return registerEmailCommand.execute(userMessage);
        }
    }

    @Override
    public CommandName getCommandName() {
        return REGISTER_NAMES;
    }
}