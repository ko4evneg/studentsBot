package ru.trainithard.pollerbot.service;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.trainithard.pollerbot.service.dto.UserMessage;

import java.io.IOException;

public interface HomeworkFilesStorageService {
    void save(UserMessage userMessage) throws TelegramApiException, IOException;
}
