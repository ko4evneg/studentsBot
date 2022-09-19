package ru.trainithard.pollerbot.service.command.adminuser;

import org.springframework.beans.factory.annotation.Autowired;
import ru.trainithard.pollerbot.service.HomeworkFilesStorageService;
import ru.trainithard.pollerbot.service.command.AbstractCommand;
import ru.trainithard.pollerbot.service.command.CommandName;
import ru.trainithard.pollerbot.util.StringMetaDataManager;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public abstract class FileButtonsCommand extends AbstractCommand {
    protected final int BUTTONS_IN_ROW = 3;
    @Autowired
    protected StringMetaDataManager metaDataManager;
    @Autowired
    protected HomeworkFilesStorageService storageService;

    protected String[] getStudentHomeworkButtons(List<String> pathNames, String param, CommandName nextCommand) {
        return pathNames.stream()
                .flatMap(name -> Stream.of(name, getMetaDataCommand(param, nextCommand, name)))
                .toArray(String[]::new);
    }

    private String getMetaDataCommand(String param, CommandName nextCommand, String name) {
        return metaDataManager.addMetaData(nextCommand.toString(), param, name);
    }

    protected int[] getMarkup(String[] dirs) {
        int fullRows = dirs.length / 2 / BUTTONS_IN_ROW;
        int lastRowButtonsQuantity = (dirs.length / 2) % BUTTONS_IN_ROW;
        int[] markup;
        if (lastRowButtonsQuantity == 0) {
            markup = new int[fullRows];
            Arrays.fill(markup, BUTTONS_IN_ROW);
        } else {
            markup = new int[fullRows + 1];
            Arrays.fill(markup, BUTTONS_IN_ROW);
            markup[markup.length - 1] = lastRowButtonsQuantity;
        }
        return markup;
    }
}
