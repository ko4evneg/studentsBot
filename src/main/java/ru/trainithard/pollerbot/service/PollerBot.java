package ru.trainithard.pollerbot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@RequiredArgsConstructor
public class PollerBot extends TelegramLongPollingBot {
    private final UpdateProcessingManager manager;

    @Override
    public String getBotToken() {
        return System.getenv("bot_token");
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            execute(manager.process(update));
        } catch (TelegramApiException e) {
            //todo catch
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return System.getenv("bot_username");
    }
}
