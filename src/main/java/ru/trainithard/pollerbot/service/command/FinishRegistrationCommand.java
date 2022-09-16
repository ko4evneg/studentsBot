package ru.trainithard.pollerbot.service.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import ru.trainithard.pollerbot.model.Role;
import ru.trainithard.pollerbot.service.dto.UserMessage;

import static ru.trainithard.pollerbot.service.command.CommandName.FINISH_REGISTRATION;

@Component
public class FinishRegistrationCommand extends AbstractCommand {
    @Override
    public BotApiMethodMessage execute(UserMessage userMessage) {
        getUser(userMessage).setRole(Role.USER);
        saveUser(userMessage);

        return getTextButtonMessage(userMessage);
    }


    @Override
    public CommandName getCommandName() {
        return FINISH_REGISTRATION;
    }
}
