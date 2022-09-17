package ru.trainithard.pollerbot.service.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import ru.trainithard.pollerbot.model.Role;
import ru.trainithard.pollerbot.service.command.regularuser.RegularUserMenuCommand;
import ru.trainithard.pollerbot.service.dto.UserMessage;

@Component
public class DelevateCommand extends RoleChangeCommand {
    @Autowired
    private RegularUserMenuCommand regularUserMenuCommand;

    @Override
    public BotApiMethodMessage execute(UserMessage userMessage) {
        return setRoleSession(userMessage, Role.USER) ?
                regularUserMenuCommand.execute(userMessage) :
                getErrorMessage(userMessage);
    }

    @Override
    public CommandName getCommandName() {
        return CommandName.DELEVATE;
    }
}
