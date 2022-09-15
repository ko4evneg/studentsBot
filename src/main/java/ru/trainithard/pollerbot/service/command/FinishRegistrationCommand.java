package ru.trainithard.pollerbot.service.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import ru.trainithard.pollerbot.model.Role;
import ru.trainithard.pollerbot.service.dto.UserMessage;
import ru.trainithard.pollerbot.util.MessageConstructor;

import java.util.List;

import static ru.trainithard.pollerbot.service.command.CommandName.*;

@Component
public class FinishRegistrationCommand extends AbstractCommand {
    @Override
    public BotApiMethodMessage execute(UserMessage userMessage) {
        getUser(userMessage).setRole(Role.USER);
        userMessage.setSession(sessionService.get(userMessage.getUser()));
        saveUserSession(userMessage);

        return getTextButtonMessage(userMessage);
    }


    @Override
    public CommandName getCommandName() {
        return FINISH_REGISTRATION;
    }
}
