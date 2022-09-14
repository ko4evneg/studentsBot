package ru.trainithard.pollerbot.service.component;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.trainithard.pollerbot.service.command.Command;
import ru.trainithard.pollerbot.service.command.CommandName;

import java.util.Map;
import java.util.Objects;

import static ru.trainithard.pollerbot.service.command.CommandName.*;

@Component
@RequiredArgsConstructor
public class CommandFinder {
    private final Map<CommandName, Command> commands;

    /**
     * @param update
     * @return first matched Command: POLL_ANSWER_HANDLER -> button-invoked command -> text command -> INPUT_HANDLER
     */
    public Command find(Update update) {
        if (update.hasPollAnswer()) {
            return getCommand(POLL_ANSWER_HANDLER);
        }

        if (update.hasCallbackQuery()) {
            String message = update.getCallbackQuery().getData();
            Command command = getCommandByAnyName(message);
            if (!Objects.isNull(command)) {
                return command;
            }
        }

        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText().trim();
            Command command = getCommand(getByName(message));
            if (!Objects.isNull(command)) {
                return command;
            } else {
                return getCommand(INPUT_HANDLER);
            }
        }

        return getCommand(NO_COMMAND);
    }

    private Command getCommand(CommandName input) {
        return commands.get(input);
    }

    private Command getCommandByAnyName(String message) {
        Command commandByInstanceName = getCommand(valueOf(message));
        return (commandByInstanceName != null) ? commandByInstanceName : getCommand(getByName(message));
    }
}
