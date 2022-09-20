package ru.trainithard.pollerbot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.trainithard.pollerbot.service.component.HandlerFinder;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class PollerBot extends TelegramLongPollingBot {
    private final HandlerFinder handlerFinder;
    private final PollerBotProxy pollerBotProxy;

    @PostConstruct
    public void init() {
        pollerBotProxy.setPollerBot(this);
    }

    @Value(value = "#{environment.botToken}")
    private String token;
    @Value(value = "#{environment.botUsername}")
    private String username;

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            execute(handlerFinder.find(update).handle(update));
        } catch (Exception e) {
            //todo catch
            e.printStackTrace();
        }
    }
}
