package ru.trainithard.pollerbot.model;

import ru.trainithard.pollerbot.service.command.CommandName;


public interface Session {
    void stepForward();

    CommandName getCurrentCommandName();

    void resetSession();
}

