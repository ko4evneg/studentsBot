package ru.trainithard.pollerbot.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import ru.trainithard.pollerbot.service.dto.UserMessage;
import ru.trainithard.pollerbot.service.validator.HomeworkFileValidator;

@Component
@RequiredArgsConstructor
public class UploadHomeworkCommand extends AbstractCommand {
    private final HomeworkFileValidator validator;
    private final FinishUploadHomeworkCommand finishUploadHomeworkCommand;

    @Override
    public BotApiMethodMessage execute(UserMessage userMessage) {
        if (isFirstInvocation(userMessage)) {
            saveUserSession(shiftSessionToThisCommand(userMessage));
            return getTextMessage(userMessage);
        }
        if (!validator.validate(userMessage)) {
            return getErrorMessage(userMessage);
        } else {
            //todo upload
            getSession(userMessage).setNextCommandName(CommandName.FINISH_UPLOAD_HOMEWORK);
            saveUserSession(userMessage);
            return finishUploadHomeworkCommand.execute(userMessage);
        }
    }

    @Override
    public CommandName getCommandName() {
        return CommandName.USER_UPLOAD_HOMEWORK;
    }
}
