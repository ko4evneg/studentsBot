package ru.trainithard.pollerbot.service.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import ru.trainithard.pollerbot.service.dto.UserMessage;

@Component
public class ResetSessionCommand extends AbstractCommand {
    @Override
    public BotApiMethodMessage execute(UserMessage userMessage) {
        getSession(userMessage).setPreviousCommandName(getStartSessionCommand(userMessage));
        getSession(userMessage).setNextCommandName(getStartSessionCommand(userMessage));
        return getTextMessage(userMessage);
    }

    private CommandName getStartSessionCommand(UserMessage userMessage) {
        return getUser(userMessage).getRole().getStartCommand();
    }

    @Override
    public CommandName getCommandName() {
        return CommandName.RESET_SESSION;
    }
}
