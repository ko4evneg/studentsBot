package ru.trainithard.pollerbot.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.trainithard.pollerbot.exception.PollerBotException;
import ru.trainithard.pollerbot.model.Role;

@Service
public class NotificationService {
    private PollerBot pollerBot;
    private UserService userService;

    public NotificationService(@Lazy PollerBot pollerBot, UserService userService) {
        this.pollerBot = pollerBot;
        this.userService = userService;
    }

    public void notifyAll(String messageText) {
        userService.getAllChatIds()
                .forEach((chatId) -> trySend(messageText, chatId));
    }

    public void notifyAdmins(String messageText) {
        userService.getByRole(Role.ADMIN)
                .forEach((user) -> trySend(messageText, user.getChatId()));
    }

    private void trySend(String messageText, Long chatId) {
        try {
            pollerBot.execute(new SendMessage(chatId.toString(), messageText));
        } catch (TelegramApiException e) {
            throw new PollerBotException("Can't send message with text: " + messageText, e);
        }
    }
}
