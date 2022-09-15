package ru.trainithard.pollerbot.service;

import org.telegram.telegrambots.meta.api.objects.Document;

public interface HomeworkFilesStorageService {
    void save(Document document);
}
