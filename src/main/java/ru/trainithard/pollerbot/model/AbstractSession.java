package ru.trainithard.pollerbot.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import ru.trainithard.pollerbot.service.command.Command;
import ru.trainithard.pollerbot.service.command.CommandName;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public abstract class AbstractSession implements Session {
    @Autowired
    protected Map<CommandName, Command>  commands;
    protected List<Command> sessionCommands;
    protected int commandIndex;

    public AbstractSession() {
        commandIndex = 0;
    }

    @Override
    public void shiftForward(int numberOfSteps) {
        commandIndex += numberOfSteps;
    }

    @Override
    public Command getCommand(CommandName name) {
        return commands.get(name);
    }

    public Command getCurrentCommand() {
        return sessionCommands.get(commandIndex);
    }

    public void resetSession() {
        commandIndex = 0;
    }
}
