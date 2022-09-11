package ru.trainithard.pollerbot.service.component;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.trainithard.pollerbot.service.command.Command;
import ru.trainithard.pollerbot.service.command.CommandName;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class CommandFinder {
    private final Map<CommandName, Command> commands;

    public Command find(CommandName commandName) {
        return commands.get(commandName);
    }
}
