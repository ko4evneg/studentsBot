package ru.trainithard.pollerbot.service.command.adminuser;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import ru.trainithard.pollerbot.service.command.CommandName;
import ru.trainithard.pollerbot.service.dto.UserMessage;

import static ru.trainithard.pollerbot.service.command.CommandName.DOWNLOAD_HOMEWORK;
import static ru.trainithard.pollerbot.service.command.CommandName.LIST_HOMEWORKS_FOLDERS;

@Component
@RequiredArgsConstructor
public class ListHomeworksCommand extends FileButtonsCommand {
    @Override
    public BotApiMethodMessage execute(UserMessage userMessage) {
        saveSessionPreviousCommand(userMessage);
        String[] dirs = getStudentHomeworkButtons(storageService.getStudentFolderNames(), "dir", DOWNLOAD_HOMEWORK);
        return getCustomButtonsMessage(userMessage, getMarkup(dirs), dirs);
    }

    @Override
    public CommandName getCommandName() {
        return LIST_HOMEWORKS_FOLDERS;
    }
}
