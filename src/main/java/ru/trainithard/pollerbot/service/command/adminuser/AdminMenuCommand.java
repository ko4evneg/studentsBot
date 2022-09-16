package ru.trainithard.pollerbot.service.command.adminuser;

import org.springframework.stereotype.Component;
import ru.trainithard.pollerbot.service.command.CommandName;
import ru.trainithard.pollerbot.service.command.StartRoleCommand;

import static ru.trainithard.pollerbot.service.command.CommandName.ADMIN_GET_MENU;

@Component
public class AdminMenuCommand extends StartRoleCommand {
    @Override
    public CommandName getCommandName() {
        return ADMIN_GET_MENU;
    }
}
