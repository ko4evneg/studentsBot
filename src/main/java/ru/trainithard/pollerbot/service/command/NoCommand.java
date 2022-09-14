package ru.trainithard.pollerbot.service.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.trainithard.pollerbot.exception.PollerBotException;
import ru.trainithard.pollerbot.service.dto.UpdateUserSession;

@Component
public class NoCommand extends AbstractCommand {
    @Override
    public SendMessage execute(UpdateUserSession updateUserSession) {
        throw new PollerBotException("ERROR: UNKNOWN COMMAND EXECUTION.");
    }

    @Override
    public String getCommandName() {
        return "NO_COMMAND";
    }
}
