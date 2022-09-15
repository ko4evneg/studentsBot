package ru.trainithard.pollerbot.service.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import ru.trainithard.pollerbot.service.dto.UserMessage;
import ru.trainithard.pollerbot.util.MessageConstructor;

import java.util.List;

import static ru.trainithard.pollerbot.service.command.CommandName.*;

@Component
public class RegularUserMenuCommand extends AbstractCommand {
    @Override
    public BotApiMethodMessage execute(UserMessage userMessage) {
        return getTextButtonMessage(userMessage);
    }

    private List<List<MessageConstructor.Button>> getMessageButtons() {
        return List.of(List.of(new MessageConstructor.Button("Начать регистрацию", REGISTER_NAMES)));
    }

    @Override
    public CommandName getCommandName() {
        return USER_GET_MENU;
    }
}
