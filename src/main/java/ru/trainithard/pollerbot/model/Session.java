package ru.trainithard.pollerbot.model;

import lombok.Getter;
import lombok.Setter;
import ru.trainithard.pollerbot.service.command.CommandName;

@Setter
public class Session{
    protected CommandName previousCommandName;
    protected CommandName nextCommandName;
    @Getter
    private Role role;

    public Session(Role role) {
        this.role = role;
    }

    public CommandName getNextCommandName() {
        return nextCommandName == null ? getRole().getStartCommand() : nextCommandName;
    }

    public CommandName getPreviousCommandName() {
        return previousCommandName == null ? getRole().getStartCommand() : previousCommandName;
    }
}
