package ru.trainithard.pollerbot.repository;

import ru.trainithard.pollerbot.service.command.CommandName;
import ru.trainithard.pollerbot.service.dto.MessageKeyboard;

public interface MessageKeyboardRepository {
    MessageKeyboard find(CommandName commandName);
}
