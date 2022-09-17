package ru.trainithard.pollerbot.service.command.newuser;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import ru.trainithard.pollerbot.service.command.AbstractCommand;
import ru.trainithard.pollerbot.service.command.CommandName;
import ru.trainithard.pollerbot.service.dto.UserMessage;
import ru.trainithard.pollerbot.service.validator.EmailValidator;

@Component
@RequiredArgsConstructor
public class RegisterEmailCommand extends AbstractCommand {
    private final EmailValidator validator;
    private final FinishRegistrationCommand finishRegistrationCommand;

    @Override
    public BotApiMethodMessage execute(UserMessage userMessage) {
        if (isFirstInvocation(userMessage)) {
            userMessage.setPreviousCommandName(getCommandName());
            saveSession(userMessage);
            return getTextMessage(userMessage);
        }

        if (!validator.validate(userMessage.getMessage())) {
            return getErrorMessage(userMessage);
        } else {
            getUser(userMessage).setEmail(userMessage.getMessage());
            userMessage.setNextCommandName(CommandName.FINISH_REGISTRATION);
            saveUserSession(userMessage);
            return finishRegistrationCommand.execute(userMessage);
        }
    }

    @Override
    public CommandName getCommandName() {
        return CommandName.REGISTER_EMAIL;
    }
}
