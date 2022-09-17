package ru.trainithard.pollerbot.repository;

import ru.trainithard.pollerbot.service.command.CommandName;
import ru.trainithard.pollerbot.util.MessageConstructor;

import java.util.List;

public interface CommandNameReplyRepository {
    String findText(CommandName commandName);

    String findErrorText(CommandName commandName);

    List<List<MessageConstructor.Button>> findButtons(CommandName commandName);
}
