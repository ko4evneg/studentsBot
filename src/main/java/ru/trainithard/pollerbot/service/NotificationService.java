package ru.trainithard.pollerbot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.trainithard.pollerbot.exception.PollerBotException;
import ru.trainithard.pollerbot.model.Role;
import ru.trainithard.pollerbot.model.User;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final UserService userService;
    private final PollerBotProxy pollerBot;

    public void notifyAll(String messageText) {
        userService.findAllChatIds()
                .forEach((chatId) -> trySend(messageText, chatId));
    }

    public void notifyAdmins(SendMessage sendMessage) {
        userService.findByRole(Role.ADMIN)
                .forEach((user) -> {
                    sendMessage.setChatId(user.getChatId());
                    trySend(sendMessage);
                });
    }

    private void trySend(SendMessage sendMessage) {
        try {
            pollerBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new PollerBotException("Can't send message with text: " + sendMessage.getText(), e);
        }
    }

    public void notifyAdmins(String messageText) {
        userService.findByRole(Role.ADMIN)
                .forEach((user) -> trySend(messageText, user.getChatId()));
    }

    private void trySend(String messageText, Long chatId) {
        try {
            pollerBot.execute(new SendMessage(chatId.toString(), messageText));
        } catch (TelegramApiException e) {
            throw new PollerBotException("Can't send message with text: " + messageText, e);
        }
    }

    public void notifyUser(Long userId, SendMessage sendMessage) {
        User user = userService.find(userId);
        trySend(text, user.getChatId());
    }
}
