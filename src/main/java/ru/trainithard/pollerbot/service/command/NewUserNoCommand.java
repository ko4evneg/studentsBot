package ru.trainithard.pollerbot.service.command;

import org.springframework.stereotype.Component;

import static ru.trainithard.pollerbot.service.command.CommandName.NO_COMMAND;

@Component
public class NewUserNoCommand extends StartRoleCommand {
    @Override
    public CommandName getCommandName() {
        return NO_COMMAND;
    }
}
