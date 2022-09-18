package ru.trainithard.pollerbot.service.command.adminuser;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import ru.trainithard.pollerbot.service.HomeworkFilesStorageService;
import ru.trainithard.pollerbot.service.command.AbstractCommand;
import ru.trainithard.pollerbot.service.command.CommandName;
import ru.trainithard.pollerbot.util.MessageConstructor;
import ru.trainithard.pollerbot.util.StringMetaDataManager;

import java.util.List;

public abstract class FileButtonsCommand extends AbstractCommand {
    @Autowired
    protected StringMetaDataManager metaDataManager;
    @Autowired
    protected HomeworkFilesStorageService storageService;

    protected List<List<MessageConstructor.Button>> getStudentHomeworkFolderButtons(List<String> pathNames, String param,
                                                                                    CommandName nextCommand, int buttonsInRow) {
        List<List<String>> studentHomeworkFolderNamesMarkedUp = Lists.partition(pathNames, buttonsInRow);
        return studentHomeworkFolderNamesMarkedUp.stream()
                .map(folderNames -> toButtons(folderNames, param, nextCommand))
                .toList();
    }

    private List<MessageConstructor.Button> toButtons(List<String> folderNames, String param, CommandName nextCommand) {
        return folderNames.stream()
                .map((folderName) -> new MessageConstructor.Button(folderName,
                        metaDataManager.addMetaData(nextCommand.toString(), param, folderName)))
                .toList();
    }
}
