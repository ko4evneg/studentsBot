package ru.trainithard.pollerbot.model;

import lombok.Getter;
import ru.trainithard.pollerbot.service.command.CommandName;

public class Session {
    private CommandName previousCommandName;
    private CommandName nextCommandName;
    @Getter
    private Role role;
    @Getter
    private long version;

    public Session(Role role) {
        this.role = role;
    }

    public void reset() {
        setPreviousCommandName(getRole().getStartCommand());
        setNextCommandName(getRole().getStartCommand());
    }

    public CommandName getNextCommandName() {
        return nextCommandName == null ? getRole().getStartCommand() : nextCommandName;
    }

    public void setNextCommandName(CommandName nextCommandName) {
        version++;
        this.nextCommandName = nextCommandName;
    }

    public CommandName getPreviousCommandName() {
        return previousCommandName == null ? getRole().getStartCommand() : previousCommandName;
    }

    public void setPreviousCommandName(CommandName previousCommandName) {
        version++;
        this.previousCommandName = previousCommandName;
    }

    public void setPreviousNextCommandNames(CommandName previousCommandName, CommandName nextCommandName) {
        version++;
        this.previousCommandName = previousCommandName;
        this.nextCommandName = nextCommandName;
    }
}
