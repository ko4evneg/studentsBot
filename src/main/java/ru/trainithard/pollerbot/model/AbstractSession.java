package ru.trainithard.pollerbot.model;

import lombok.Getter;
import lombok.Setter;
import ru.trainithard.pollerbot.service.command.CommandName;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public abstract class AbstractSession implements Session {
    protected boolean newSession;
    protected List<CommandName> commandNames = new ArrayList<>();
    protected int commandIndex;

    @Override
    public void stepForward() {
        commandIndex++;
    }

    public CommandName getCurrentCommandName() {
        return commandNames.get(commandIndex);
    }

    public void resetSession() {
        commandIndex = 0;
    }
}
