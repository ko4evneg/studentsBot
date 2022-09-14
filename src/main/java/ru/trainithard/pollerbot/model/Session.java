package ru.trainithard.pollerbot.model;

import ru.trainithard.pollerbot.service.command.Command;


public interface Session {
    void stepForward();

    Command getCurrentCommandName();

    void resetSession();
}

