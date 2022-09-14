package ru.trainithard.pollerbot.model;

import ru.trainithard.pollerbot.service.command.Command;
import ru.trainithard.pollerbot.service.command.CommandName;


public interface Session {
    void shiftForward(int numberOfSteps);

    Command getCommand(CommandName name);

    Command getCurrentCommand();

    void resetSession();
}

