package ru.trainithard.pollerbot.service.command;

import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import ru.trainithard.pollerbot.service.dto.UserMessage;

public abstract class StartRoleCommand extends AbstractCommand {
    @Override
    public BotApiMethodMessage execute(UserMessage userMessage) {
        userMessage.getSession().reset();
        saveSession(userMessage);
        return getStandardMessage(userMessage);
    }
}
