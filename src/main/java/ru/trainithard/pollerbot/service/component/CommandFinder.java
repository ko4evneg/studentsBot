package ru.trainithard.pollerbot.service.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.trainithard.pollerbot.service.command.Command;

import java.util.Map;

@Component
public class CommandFinder {
    private static final String COMMAND_PREFIX = "/";
    @Autowired
    @Qualifier("commands")
    private Map<String, Command> commands;

    public boolean hasCommand(Update update) {
        return find(update) != commands.get("NO_COMMAND");
    }

    public Command find(Update update) {
        if (update.hasCallbackQuery()) {
            String message = update.getCallbackQuery().getData();
            return commands.get(message);
        }

        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText().trim();
            if (message.startsWith(COMMAND_PREFIX)) {
                return commands.get(message.substring(1));
            }
        }

        return commands.get("NO_COMMAND");
    }
}
