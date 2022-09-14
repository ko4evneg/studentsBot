package ru.trainithard.pollerbot.service.command;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.trainithard.pollerbot.service.dto.UpdateUserSession;

public interface Command {
    SendMessage execute(UpdateUserSession updateUserSession);

    String getCommandName();

    String getSessionClassName();

    int getStepNumber();
}
