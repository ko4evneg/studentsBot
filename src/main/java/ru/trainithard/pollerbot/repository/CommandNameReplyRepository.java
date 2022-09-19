package ru.trainithard.pollerbot.repository;

import ru.trainithard.pollerbot.service.command.CommandName;

public interface CommandNameReplyRepository {
    String findText(CommandName commandName);

}
