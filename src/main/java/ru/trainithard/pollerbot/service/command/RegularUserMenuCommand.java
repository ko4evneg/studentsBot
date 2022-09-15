package ru.trainithard.pollerbot.service.command;

import org.springframework.stereotype.Component;

import static ru.trainithard.pollerbot.service.command.CommandName.USER_GET_MENU;

@Component
public class RegularUserMenuCommand extends StartRoleCommand {
    @Override
    public CommandName getCommandName() {
        return USER_GET_MENU;
    }
}
