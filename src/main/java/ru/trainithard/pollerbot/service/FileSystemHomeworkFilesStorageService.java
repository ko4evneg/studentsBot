package ru.trainithard.pollerbot.service;

import com.google.common.io.Files;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.trainithard.pollerbot.service.dto.UserMessage;

import java.io.*;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileSystemHomeworkFilesStorageService implements HomeworkFilesStorageService {
    @Value("${pollerbot.homework.directory}")
    private String storageDirectory;
    private final PollerBotProxy pollerBot;

    @Override
    public void save(UserMessage userMessage) throws TelegramApiException, IOException {
        File destinationFile = new File(storageDirectory + "/" + userMessage.getEmailInFileFormat() + "/" + getDocument(userMessage).getFileName());
        Files.createParentDirs(destinationFile);
        try (BufferedOutputStream outputStream = getBufferedOutputStream(destinationFile)) {
            org.telegram.telegrambots.meta.api.objects.File file = pollerBot.execute(getGetFile(getDocument(userMessage)));
            File sourceFile = pollerBot.downloadFile(file);
            Files.copy(sourceFile, outputStream);
        }
    }

    private Document getDocument(UserMessage userMessage) {
        return userMessage.getUpdate().getMessage().getDocument();
    }

    private BufferedOutputStream getBufferedOutputStream(File destinationFile) throws FileNotFoundException {
        return new BufferedOutputStream(new FileOutputStream(destinationFile, false));
    }

    private GetFile getGetFile(Document document) {
        GetFile getFile = new GetFile();
        getFile.setFileId(document.getFileId());
        return getFile;
    }

    @Override
    public List<String> getStudentFolderNames() {
        File homeworkRootDirectory = new File(storageDirectory);
        File[] homeworkDirectories = homeworkRootDirectory.listFiles(File::isDirectory);
        return Arrays.stream(homeworkDirectories)
                .map(File::getName)
                .toList();
    }

    @Override
    public List<String> getStudentHomeworkFiles(String studentDirectoryName) {
        File homeworkRootDirectory = new File(storageDirectory + "/" + studentDirectoryName);
        File[] files = homeworkRootDirectory.listFiles();
        return Arrays.stream(files)
                .map(File::getName)
                .toList();
    }

    @Override
    public File getHomeworkFile(String homeworkFolder, String homeworkFile) {
        return new File(storageDirectory + "/" + homeworkFolder + "/" + homeworkFile);
    }
}
