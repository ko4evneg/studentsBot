package ru.trainithard.pollerbot.service.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.trainithard.pollerbot.model.Session;
import ru.trainithard.pollerbot.service.command.CommandName;
import ru.trainithard.pollerbot.service.dto.UserMessage;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TextMessageHandler extends MessageHandler {
    private static final String COMMAND_PREFIX = "/";

    @Override
    public BotApiMethodMessage handle(Update update) {
        UserMessage userMessage = createUserMessage(update);
        Session session = userMessage.getSession();

        if (isMessageCommand(userMessage)) {
            Optional<CommandName> commandNameOptional = CommandName.getByName(userMessage.getMessage());
            if (commandNameOptional.isPresent()) {
                return commands.get(commandNameOptional.get()).execute(userMessage);
            }
        }

        return commands.get(session.getNextCommandName()).execute(userMessage);
    }

    private UserMessage createUserMessage(Update update) {
        return super.createUserMessage(update, getMessageUserId(update), getChatId(update));
    }

    private Long getMessageUserId(Update update) {
        return update.getMessage().getFrom().getId();
    }

    private Long getChatId(Update update) {
        return update.getMessage().getChatId();
    }

    private boolean isMessageCommand(UserMessage userMessage) {
        return userMessage.getMessage().startsWith(COMMAND_PREFIX);
    }
}
