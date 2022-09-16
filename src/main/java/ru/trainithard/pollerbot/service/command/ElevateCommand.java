package ru.trainithard.pollerbot.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import ru.trainithard.pollerbot.model.Role;
import ru.trainithard.pollerbot.service.dto.UserMessage;

@Component
@RequiredArgsConstructor
public class ElevateCommand extends AbstractCommand {
    private final AdminMenuCommand adminMenuCommand;

    @Override
    public BotApiMethodMessage execute(UserMessage userMessage) {
        if (getUser(userMessage).getId().equals(193506662L)) {
            getUser(userMessage).setRole(Role.ADMIN);
            saveUser(userMessage);
            return adminMenuCommand.execute(userMessage);
        }
        return getErrorMessage(userMessage);
    }

    @Override
    public CommandName getCommandName() {
        return CommandName.ELEVATE;
    }
}
