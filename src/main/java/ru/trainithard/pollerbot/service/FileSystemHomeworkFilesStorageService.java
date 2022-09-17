package ru.trainithard.pollerbot.service;

import com.google.common.io.Files;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.trainithard.pollerbot.exception.PollerBotException;

import java.io.*;

@Service
public class FileSystemHomeworkFilesStorageService implements HomeworkFilesStorageService {
    @Value("${pollerbot.homework.directory}")
    private String storageDirectory;
    private PollerBot pollerBot;

    public FileSystemHomeworkFilesStorageService(@Lazy PollerBot pollerBot) {
        this.pollerBot = pollerBot;
    }

    @Override
    public void save(Document document) {
        //todo student folder
        File destinationFile = new File(storageDirectory + "/" + document.getFileName());
        try (BufferedOutputStream outputStream = getBufferedOutputStream(destinationFile)) {
            org.telegram.telegrambots.meta.api.objects.File file = pollerBot.execute(getGetFile(document));
            File sourceFile = pollerBot.downloadFile(file);
            Files.copy(sourceFile, outputStream);
        } catch (IOException | TelegramApiException e) {
            throw new PollerBotException("Can't save file!", e);
        }
    }

    private BufferedOutputStream getBufferedOutputStream(File destinationFile) throws FileNotFoundException {
        return new BufferedOutputStream(new FileOutputStream(destinationFile, false));
    }

    private GetFile getGetFile(Document document) {
        GetFile getFile = new GetFile();
        getFile.setFileId(document.getFileId());
        return getFile;
    }
}
