package ru.trainithard.pollerbot.service.command.regularuser;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import ru.trainithard.pollerbot.service.command.CommandName;
import ru.trainithard.pollerbot.service.command.StartRoleCommand;
import ru.trainithard.pollerbot.service.dto.UserMessage;

import static ru.trainithard.pollerbot.service.command.CommandName.LESSONS_MENU;

@Component
public class LessonsMenuCommand extends StartRoleCommand {
    @Override
    public BotApiMethodMessage execute(UserMessage userMessage) {
        return getTextButtonMessage(userMessage);
    }

    @Override
    public CommandName getCommandName() {
        return LESSONS_MENU;
    }
}
