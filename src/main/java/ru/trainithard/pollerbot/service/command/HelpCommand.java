package ru.trainithard.pollerbot.service.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import ru.trainithard.pollerbot.service.dto.UserMessage;

@Component
public class HelpCommand extends AbstractCommand {
    @Override
    public BotApiMethodMessage execute(UserMessage userMessage) {
        saveSession(shiftSessionToThisCommand(userMessage));
        return getTextMessage(userMessage);
    }

    @Override
    public CommandName getCommandName() {
        return CommandName.HELP;
    }
}
