package ru.trainithard.pollerbot.service.command.authpending;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import ru.trainithard.pollerbot.service.command.CommandName;
import ru.trainithard.pollerbot.service.command.StartRoleCommand;
import ru.trainithard.pollerbot.service.dto.UserMessage;

import static ru.trainithard.pollerbot.service.command.CommandName.AUTH_PENDING;

@Component
public class AuthPendingCommand extends StartRoleCommand {
    @Override
    public BotApiMethodMessage execute(UserMessage userMessage) {
        userMessage.getSession().reset();
        saveSession(userMessage);
        return getTextMessage(userMessage);
    }

    @Override
    public CommandName getCommandName() {
        return AUTH_PENDING;
    }
}
