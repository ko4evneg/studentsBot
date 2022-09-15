package ru.trainithard.pollerbot.service.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import ru.trainithard.pollerbot.service.dto.UserMessage;

import java.util.List;

import static ru.trainithard.pollerbot.service.command.CommandName.NO_COMMAND;
import static ru.trainithard.pollerbot.service.command.CommandName.REGISTER_NAMES;
import static ru.trainithard.pollerbot.util.MessageConstructor.Button;

@Component
public class NewUserNoCommand extends AbstractCommand {
    @Override
    public BotApiMethodMessage execute(UserMessage userMessage) {
        return getTextButtonMessage(userMessage);
    }

    @Override
    public CommandName getCommandName() {
        return NO_COMMAND;
    }
}
