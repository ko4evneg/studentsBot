package ru.trainithard.pollerbot.service.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import ru.trainithard.pollerbot.service.dto.UserMessage;

@Component
public class ResetSessionCommand extends AbstractCommand {
    @Override
    public BotApiMethodMessage execute(UserMessage userMessage) {
        getSession(userMessage).reset();
        saveSession(userMessage);
        return getTextMessage(userMessage);
    }

    @Override
    public CommandName getCommandName() {
        return CommandName.RESET_SESSION;
    }
}
