package ru.trainithard.pollerbot.service.command.regularuser;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import ru.trainithard.pollerbot.service.command.AbstractCommand;
import ru.trainithard.pollerbot.service.command.CommandName;
import ru.trainithard.pollerbot.service.dto.UserMessage;

import static ru.trainithard.pollerbot.service.command.CommandName.FINISH_UPLOAD_HOMEWORK;

@Component
public class FinishUploadHomeworkCommand extends AbstractCommand {

    @Override
    public BotApiMethodMessage execute(UserMessage userMessage) {
        return getStandardMessage(userMessage);
    }

    @Override
    public CommandName getCommandName() {
        return FINISH_UPLOAD_HOMEWORK;
    }
}
