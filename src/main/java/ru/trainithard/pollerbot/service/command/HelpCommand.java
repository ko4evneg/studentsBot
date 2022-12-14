package ru.trainithard.pollerbot.service.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import ru.trainithard.pollerbot.service.dto.UserMessage;

@Component
public class HelpCommand extends AbstractCommand {
    @Override
    public BotApiMethodMessage execute(UserMessage userMessage) {
        saveSessionPreviousCommand(userMessage);
        return getStandardMessage(userMessage);
    }

    @Override
    public CommandName getCommandName() {
        return CommandName.HELP;
    }
}
