package ru.trainithard.pollerbot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final PollerBot pollerBot;
    private final UserService userService;

    public void notifyAll(String messageText) throws TelegramApiException {
        for (Long chatId : userService.getAllChatIds()) {
            pollerBot.execute(new SendMessage(chatId.toString(), messageText));
        }
    }
}
