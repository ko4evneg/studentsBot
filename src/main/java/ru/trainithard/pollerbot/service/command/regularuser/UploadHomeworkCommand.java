package ru.trainithard.pollerbot.service.command.regularuser;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.trainithard.pollerbot.service.HomeworkFilesStorageService;
import ru.trainithard.pollerbot.service.NotificationService;
import ru.trainithard.pollerbot.service.command.AbstractCommand;
import ru.trainithard.pollerbot.service.command.CommandName;
import ru.trainithard.pollerbot.service.dto.UserMessage;
import ru.trainithard.pollerbot.service.validator.HomeworkFileValidator;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class UploadHomeworkCommand extends AbstractCommand {
    private final HomeworkFileValidator validator;
    private final FinishUploadHomeworkCommand finishUploadHomeworkCommand;
    private final HomeworkFilesStorageService storageService;
    private final NotificationService notificationService;

    @Override
    public BotApiMethodMessage execute(UserMessage userMessage) {
        if (isFirstInvocation(userMessage)) {
            saveSessionPreviousCommand(userMessage);
            return getTextMessage(userMessage);
        }
        if (!validator.validate(userMessage)) {
            return getErrorMessage(userMessage);
        } else {
            try {
                storageService.save(userMessage);
                userMessage.setNextCommandName(CommandName.FINISH_UPLOAD_HOMEWORK);
                saveSession(userMessage);
                notificationService.notifyAdmins(getNotifyMessage(userMessage));
                return finishUploadHomeworkCommand.execute(userMessage);
            } catch (TelegramApiException | IOException e) {
                return getCustomTextMessage(userMessage, "Ошибка сохранения файла: " + e.getMessage());
            }
        }
    }

    private String getNotifyMessage(UserMessage userMessage) {
        return String.format("Пользователь %s %s (%s) загрузил домашнее задание: %s.", userMessage.getFirstName(),
                userMessage.getLastName(), userMessage.getEmail(), getDocument(userMessage).getFileName());
    }

    private Document getDocument(UserMessage userMessage) {
        return userMessage.getUpdate().getMessage().getDocument();
    }

    @Override
    public CommandName getCommandName() {
        return CommandName.USER_UPLOAD_HOMEWORK;
    }
}
