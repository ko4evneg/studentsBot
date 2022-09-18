package ru.trainithard.pollerbot.service;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.trainithard.pollerbot.service.dto.UserMessage;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface HomeworkFilesStorageService {
    void save(UserMessage userMessage) throws TelegramApiException, IOException;

    List<String> getStudentFolderNames();

    List<String> getStudentHomeworkFiles(String studentDirectoryName);

    File getHomeworkFile(String homeworkFolder, String homeworkFile);
}
