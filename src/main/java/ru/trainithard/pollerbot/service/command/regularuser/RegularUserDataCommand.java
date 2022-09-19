package ru.trainithard.pollerbot.service.command.regularuser;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import ru.trainithard.pollerbot.service.command.AbstractCommand;
import ru.trainithard.pollerbot.service.command.CommandName;
import ru.trainithard.pollerbot.service.dto.UserMessage;

import static ru.trainithard.pollerbot.service.command.CommandName.USER_GET_DATA;

@Component
public class RegularUserDataCommand extends AbstractCommand {
    @Override
    public BotApiMethodMessage execute(UserMessage userMessage) {
        return getCustomMessage(userMessage, getUserDataMessage(userMessage));
    }

    private String getUserDataMessage(UserMessage userMessage) {
        return String.format("Ваши данные:\r\nФИО: %s %s\r\nemail: %s\r\nusername: %s", userMessage.getFirstName(),
                userMessage.getLastName(), userMessage.getEmail(), userMessage.getNickName());
    }

    @Override
    public CommandName getCommandName() {
        return USER_GET_DATA;
    }
}
