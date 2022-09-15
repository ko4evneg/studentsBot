package ru.trainithard.pollerbot.service.handler;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.trainithard.pollerbot.model.Session;
import ru.trainithard.pollerbot.service.command.CommandName;
import ru.trainithard.pollerbot.service.dto.UserMessage;

@Component
public class CallbackMessageHandler extends MessageHandler {
    @Override
    public BotApiMethodMessage handle(Update update) {
        UserMessage userMessage = createUserMessage(update);
        Session session = userMessage.getSession();
        session.setNextCommandName(getInvokedCommandName(userMessage));

        return commands.get(session.getNextCommandName()).execute(userMessage);
    }

    private CommandName getInvokedCommandName(UserMessage userMessage) {
        return CommandName.getByName(userMessage.getCallbackData()).get();
    }

    private UserMessage createUserMessage(Update update) {
        return super.createUserMessage(update, getMessageUserId(update), getChatId(update));
    }

    private Long getMessageUserId(Update update) {
        return update.getCallbackQuery().getFrom().getId();
    }

    private Long getChatId(Update update) {
        return update.getCallbackQuery().getMessage().getChatId();
    }
}
