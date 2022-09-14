package ru.trainithard.pollerbot.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import ru.trainithard.pollerbot.service.command.Command;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public abstract class AbstractSession implements Session {
    @Autowired
    protected Map<String, List<Command>> sessionCommands;
    protected List<Command> commands = new ArrayList<>();
    protected boolean newSession;
    protected int commandIndex;

    @Override
    public void stepForward() {
        commandIndex++;
    }

    public Command getCurrentCommandName() {
        return commands.get(commandIndex);
    }

    public void resetSession() {
        commandIndex = 0;
    }
}
