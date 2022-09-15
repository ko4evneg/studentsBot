package ru.trainithard.pollerbot.model;

import lombok.Setter;
import ru.trainithard.pollerbot.service.command.CommandName;

@Setter
public abstract class Session{
    protected CommandName previousCommandName;
    protected CommandName nextCommandName;

    public CommandName getNextCommandName() {
        return nextCommandName == null ? getRole().getStartCommand() : nextCommandName;
    }

    public CommandName getPreviousCommandName() {
        return previousCommandName == null ? getRole().getStartCommand() : previousCommandName;
    }

    abstract Role getRole();
}
