package ru.trainithard.pollerbot.service.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import ru.trainithard.pollerbot.model.Role;
import ru.trainithard.pollerbot.service.command.adminuser.AdminMenuCommand;
import ru.trainithard.pollerbot.service.dto.UserMessage;

@Component
public class ElevateCommand extends RoleChangeCommand {
    @Autowired
    private AdminMenuCommand adminMenuCommand;

    @Override
    public BotApiMethodMessage execute(UserMessage userMessage) {
        return setRoleSession(userMessage, Role.ADMIN) ?
                adminMenuCommand.execute(userMessage) :
                getErrorMessage(userMessage);
    }

    @Override
    public CommandName getCommandName() {
        return CommandName.ELEVATE;
    }
}
