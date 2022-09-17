package ru.trainithard.pollerbot.service;

import lombok.Setter;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.File;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.Serializable;

@Component
@Setter
//Used because Lazy Autowiring not working for FileSystemHomeworkFilesStorageService
public class PollerBotProxy {
    private PollerBot pollerBot;

    public <T extends Serializable, Method extends BotApiMethod<T>> T execute(Method method) throws TelegramApiException {
        return pollerBot.execute(method);
    }

    public final java.io.File downloadFile(File file) throws TelegramApiException {
        return pollerBot.downloadFile(file);
    }
}
