package ru.trainithard.pollerbot.model;

import lombok.Getter;
import lombok.Setter;
import ru.trainithard.pollerbot.service.command.CommandName;

import java.time.LocalDateTime;

@Getter
@Setter
public class Session {
    private CommandName previousCommandName;
    private CommandName nextCommandName;
    private Role role;
    private long version;
    private LocalDateTime lastAccessTime;
    private String notificationText;

    public Session(Role role) {
        this.role = role;
        lastAccessTime = LocalDateTime.now();
    }

    public void reset() {
        setPreviousCommandName(getRole().getStartCommand());
        setNextCommandName(getRole().getStartCommand());
        version = 0;
        notificationText = null;
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
