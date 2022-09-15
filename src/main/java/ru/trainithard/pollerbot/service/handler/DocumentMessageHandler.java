package ru.trainithard.pollerbot.service.handler;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.trainithard.pollerbot.model.Session;
import ru.trainithard.pollerbot.service.dto.UserMessage;

@Component
public class DocumentMessageHandler extends MessageHandler {
    @Override
    public BotApiMethodMessage handle(Update update) {
        UserMessage userMessage = createUserMessage(update);
        Session session = userMessage.getSession();

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
}
