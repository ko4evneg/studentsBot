package ru.trainithard.pollerbot.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import ru.trainithard.pollerbot.model.User;
import ru.trainithard.pollerbot.service.UserService;
import ru.trainithard.pollerbot.service.dto.UserMessage;

@Component
@RequiredArgsConstructor
public class ResetSessionCommand extends AbstractCommand {
    private final UserService userService;

    @Override
    public BotApiMethodMessage execute(UserMessage userMessage) {
        userMessage.setUser(getFreshUser(userMessage));
        getSession(userMessage).reset();
        saveSession(userMessage);
        return getTextMessage(userMessage);
    }

    private User getFreshUser(UserMessage userMessage) {
        Long userId = getUser(userMessage).getId();
        return userService.get(userId);
    }

    @Override
    public CommandName getCommandName() {
        return CommandName.RESET_SESSION;
    }
}
