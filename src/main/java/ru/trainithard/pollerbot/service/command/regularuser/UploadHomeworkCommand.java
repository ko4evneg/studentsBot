package ru.trainithard.pollerbot.service.command.regularuser;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import org.telegram.telegrambots.meta.api.objects.Document;
import ru.trainithard.pollerbot.service.HomeworkFilesStorageService;
import ru.trainithard.pollerbot.service.command.AbstractCommand;
import ru.trainithard.pollerbot.service.command.CommandName;
import ru.trainithard.pollerbot.service.dto.UserMessage;
import ru.trainithard.pollerbot.service.validator.HomeworkFileValidator;

@Component
@RequiredArgsConstructor
public class UploadHomeworkCommand extends AbstractCommand {
    private final HomeworkFileValidator validator;
    private final FinishUploadHomeworkCommand finishUploadHomeworkCommand;
    @Lazy
    @Autowired
    private HomeworkFilesStorageService storageService;

    @Override
    public BotApiMethodMessage execute(UserMessage userMessage) {
        if (isFirstInvocation(userMessage)) {
            saveSession(shiftSessionToThisCommand(userMessage));
            return getTextMessage(userMessage);
        }
        if (!validator.validate(userMessage)) {
            return getErrorMessage(userMessage);
        } else {
            storageService.save(getDocument(userMessage));
            getSession(userMessage).setNextCommandName(CommandName.FINISH_UPLOAD_HOMEWORK);
            saveSession(userMessage);
            return finishUploadHomeworkCommand.execute(userMessage);
        }
    }

    private Document getDocument(UserMessage userMessage) {
        return userMessage.getUpdate().getMessage().getDocument();
    }

    @Override
    public CommandName getCommandName() {
        return CommandName.USER_UPLOAD_HOMEWORK;
    }
}
