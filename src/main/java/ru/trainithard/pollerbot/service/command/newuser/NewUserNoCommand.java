package ru.trainithard.pollerbot.service.command.newuser;

import org.springframework.stereotype.Component;
import ru.trainithard.pollerbot.service.command.CommandName;
import ru.trainithard.pollerbot.service.command.StartRoleCommand;

import static ru.trainithard.pollerbot.service.command.CommandName.NO_COMMAND;

@Component
public class NewUserNoCommand extends StartRoleCommand {
    @Override
    public CommandName getCommandName() {
        return NO_COMMAND;
    }
}
