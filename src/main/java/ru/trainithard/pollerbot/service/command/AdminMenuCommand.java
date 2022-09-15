package ru.trainithard.pollerbot.service.command;

import org.springframework.stereotype.Component;

import static ru.trainithard.pollerbot.service.command.CommandName.ADMIN_GET_MENU;

@Component
public class AdminMenuCommand extends StartRoleCommand {
    @Override
    public CommandName getCommandName() {
        return ADMIN_GET_MENU;
    }
}
