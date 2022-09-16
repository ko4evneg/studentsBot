package ru.trainithard.pollerbot.service.command.regularuser;

import org.springframework.stereotype.Component;
import ru.trainithard.pollerbot.service.command.CommandName;
import ru.trainithard.pollerbot.service.command.StartRoleCommand;

import static ru.trainithard.pollerbot.service.command.CommandName.USER_GET_MENU;

@Component
public class RegularUserMenuCommand extends StartRoleCommand {
    @Override
    public CommandName getCommandName() {
        return USER_GET_MENU;
    }
}
