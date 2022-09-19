package ru.trainithard.pollerbot.service.command.adminuser;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.trainithard.pollerbot.exception.PollerBotException;
import ru.trainithard.pollerbot.service.HomeworkFilesStorageService;
import ru.trainithard.pollerbot.service.PollerBotProxy;
import ru.trainithard.pollerbot.service.command.CommandName;
import ru.trainithard.pollerbot.service.dto.UserMessage;
import ru.trainithard.pollerbot.util.StringMetaDataManager;

import java.io.File;
import java.util.List;

import static ru.trainithard.pollerbot.service.command.CommandName.DOWNLOAD_HOMEWORK;

@Component
@RequiredArgsConstructor
public class DownloadHomeworksCommand extends FileButtonsCommand {
    private final HomeworkFilesStorageService storageService;
    private final StringMetaDataManager metaDataManager;
    private final PollerBotProxy pollerBotProxy;

    @Override
    public BotApiMethodMessage execute(UserMessage userMessage) {
        if (isFirstInvocation(userMessage)) {
            String studentDirectoryName = metaDataManager.getMetaDataValue(userMessage.getCallbackData(), "dir");
            List<String> studentHomeworkFiles = storageService.getStudentHomeworkFiles(studentDirectoryName);
            String[] filesButtons = getStudentHomeworkButtons(studentHomeworkFiles, "fil", getCommandName());
            saveSessionPreviousCommand(userMessage);
            return getCustomButtonsMessage(userMessage, getMarkup(filesButtons), filesButtons);
        }

        try {
            pollerBotProxy.execute(SendDocument.builder()
                    .chatId(getUserId(userMessage))
                    .document(getDocument(getHomeworkFile(userMessage)))
                    .build());
        } catch (TelegramApiException e) {
            throw new PollerBotException("Can't pass file to user.", e);
        }

        return SendMessage.builder()
                .chatId(userMessage.getChatId())
                .text("File was sent.")
                .build();
    }

    private InputFile getDocument(File homeworkFile) {
        return new InputFile(homeworkFile);
    }

    private File getHomeworkFile(UserMessage userMessage) {
        String studentFolderName = userMessage.getEmailInFileFormat();
        String studentFileName = metaDataManager.getMetaDataValue(userMessage.getCallbackData(), "fil");
        return storageService.getHomeworkFile(studentFolderName, studentFileName);
    }

    @Override
    public CommandName getCommandName() {
        return DOWNLOAD_HOMEWORK;
    }
}
