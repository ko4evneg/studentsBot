package ru.trainithard.pollerbot.service.component;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.trainithard.pollerbot.exception.PollerBotException;
import ru.trainithard.pollerbot.service.command.CommandName;

@Component
@RequiredArgsConstructor
public class UpdateParser {
    private static final String COMMAND_PREFIX = "/";

    public Long getUserId(Update update) {
        if (update.hasMessage()) {
            return update.getMessage().getFrom().getId();
        }
        if (update.hasCallbackQuery()) {
            return update.getCallbackQuery().getFrom().getId();
        }
        if (update.hasPollAnswer()) {
            //todo: anon? poll chat id?
            return update.getPollAnswer().getUser().getId();
        }
        throw new PollerBotException("Can't extract user from update!");
    }

    public boolean hasCommand(Update update) {
        return getCommandName(update) != CommandName.NO_COMMAND;
    }

    public CommandName getCommandName(Update update) {
        if (update.hasCallbackQuery()) {
            String message = update.getCallbackQuery().getData();
            return CommandName.getByHumanName(message);
        }

        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText().trim();
            if (message.startsWith(COMMAND_PREFIX)) {
                return CommandName.getByHumanName(message.substring(1));
            }
        }

        return CommandName.NO_COMMAND;
    }
}
