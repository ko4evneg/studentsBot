package ru.trainithard.pollerbot.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import ru.trainithard.pollerbot.service.dto.UserMessage;

@Component
@RequiredArgsConstructor
public class ResetSessionCommand extends AbstractCommand {
    @Override
    public BotApiMethodMessage execute(UserMessage userMessage) {
        userMessage.setUser(getFreshUser(userMessage));
        getSession(userMessage).reset();
        saveSession(userMessage);
        return getTextMessage(userMessage);
    }

    @Override
    public CommandName getCommandName() {
        return CommandName.RESET_SESSION;
    }
}
