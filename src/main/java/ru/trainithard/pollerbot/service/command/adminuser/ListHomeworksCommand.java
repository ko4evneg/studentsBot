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
    private static final int BUTTONS_IN_ROW = 3;

    @Override
    public BotApiMethodMessage execute(UserMessage userMessage) {
        saveSessionPreviousCommand(userMessage);
        return getCustomButtonTextMessage(userMessage,
                getStudentHomeworkFolderButtons(storageService.getStudentFolderNames(), "dir",
                        DOWNLOAD_HOMEWORK, BUTTONS_IN_ROW));
    }

    @Override
    public CommandName getCommandName() {
        return LIST_HOMEWORKS_FOLDERS;
    }
}
