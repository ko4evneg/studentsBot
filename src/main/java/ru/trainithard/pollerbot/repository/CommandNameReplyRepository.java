package ru.trainithard.pollerbot.repository;

import ru.trainithard.pollerbot.service.command.CommandName;
import ru.trainithard.pollerbot.util.MessageConstructor;

import java.util.List;

public interface CommandNameReplyRepository {
    String getText(CommandName commandName);

    String getErrorText(CommandName commandName);

    List<List<MessageConstructor.Button>> getButtons(CommandName commandName);
}
