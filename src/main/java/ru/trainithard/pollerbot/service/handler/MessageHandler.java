package ru.trainithard.pollerbot.service.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.trainithard.pollerbot.model.User;
import ru.trainithard.pollerbot.service.UserService;
import ru.trainithard.pollerbot.service.command.AbstractCommand;
import ru.trainithard.pollerbot.service.command.CommandName;
import ru.trainithard.pollerbot.service.component.SessionFinder;
import ru.trainithard.pollerbot.service.dto.UserMessage;

import java.util.Map;

public abstract class MessageHandler {
    @Autowired
    protected UserService userService;
    @Autowired
    protected SessionFinder sessionFinder;
    @Autowired
    protected Map<CommandName, AbstractCommand> commands;

    public abstract BotApiMethodMessage handle(Update update);

    protected UserMessage createUserMessage(Update update, Long userId, Long...chatId) {
        User user = userService.get(userId);
        if (chatId[0] != null) {
            user.setChatId(chatId[0]);
        }

        return UserMessage.builder()
                .update(update)
                .user(user)
                .session(sessionFinder.find(userId))
                .build();
    }
}
