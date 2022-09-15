package ru.trainithard.pollerbot.service.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import ru.trainithard.pollerbot.service.dto.UserMessage;

import static ru.trainithard.pollerbot.service.command.CommandName.USER_GET_DATA;

@Component
public class RegularUserDataCommand extends AbstractCommand {
    @Override
    public BotApiMethodMessage execute(UserMessage userMessage) {
        return getCustomTextButtonMessage(userMessage, getUserDataMessage(userMessage));
    }

    private String getUserDataMessage(UserMessage userMessage) {
        return String.format("Ваши данные:\r\nФИО: %s %s\r\nemail: %S\r\n", getUser(userMessage).getFirstName(),
                getUser(userMessage).getLastName(), getUser(userMessage).getEmail());
    }

    @Override
    public CommandName getCommandName() {
        return USER_GET_DATA;
    }
}