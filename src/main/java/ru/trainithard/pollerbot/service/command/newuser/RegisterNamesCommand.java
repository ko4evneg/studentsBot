package ru.trainithard.pollerbot.service.command.newuser;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import ru.trainithard.pollerbot.service.command.AbstractCommand;
import ru.trainithard.pollerbot.service.command.CommandName;
import ru.trainithard.pollerbot.service.dto.UserMessage;
import ru.trainithard.pollerbot.service.validator.UserNamesValidator;

import static ru.trainithard.pollerbot.service.command.CommandName.REGISTER_NAMES;

@Component
@RequiredArgsConstructor
public class RegisterNamesCommand extends AbstractCommand {
    private final UserNamesValidator validator;
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
            userMessage.setNextCommandName(CommandName.FINISH_REGISTRATION);
            getUser(userMessage).setFirstName(getFirstName(userMessage));
            getUser(userMessage).setLastName(getLastName(userMessage));
            getUser(userMessage).setNickName(getNickName(userMessage));
            saveUserSession(userMessage);
            return finishRegistrationCommand.execute(userMessage);
        }
    }

    private String getFirstName(UserMessage userMessage) {
        return (userMessage.getMessage().split("\\s"))[0];
    }

    private String getLastName(UserMessage userMessage) {
        return (userMessage.getMessage().split("\\s"))[1];
    }

    private String getNickName(UserMessage userMessage) {
        return (userMessage.getUpdate().getMessage().getFrom().getUserName());
    }

    @Override
    public CommandName getCommandName() {
        return REGISTER_NAMES;
    }
}
