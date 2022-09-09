package ru.trainithard.pollerbot.service;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class PollerBot extends TelegramLongPollingBot {
    @Override
    public String getBotToken() {
        return System.getenv("bot_token");
    }

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println("woila!");
    }

    @Override
    public String getBotUsername() {
        return System.getenv("bot_username");
    }
}
