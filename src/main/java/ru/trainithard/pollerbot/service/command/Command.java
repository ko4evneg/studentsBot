package ru.trainithard.pollerbot.service.command;

import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface Command {
    /**
     * Executes command based on update data.
     * @param update
     * @return SendMessage used for reply after command execution
     */
    BotApiMethodMessage execute(Update update);

    /**
     * Used for lookup of this command by service layer.
     * @return CommandName
     */
    CommandName getCommandName();

}
