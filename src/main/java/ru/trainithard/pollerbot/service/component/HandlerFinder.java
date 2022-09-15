package ru.trainithard.pollerbot.service.component;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.trainithard.pollerbot.exception.PollerBotException;
import ru.trainithard.pollerbot.service.handler.MessageHandler;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class HandlerFinder {
    private final Map<String, MessageHandler> handler;

    public MessageHandler find(Update update) throws ReflectiveOperationException {
        if (update.hasPollAnswer()) {
            return handler.get("pollMessageHandler");
        }
        if (update.hasCallbackQuery()) {
            return handler.get("callbackMessageHandler");
        }
        if (update.hasMessage() && update.getMessage().hasText()) {
            return handler.get("textMessageHandler");
        }
        if (update.hasMessage() && update.getMessage().hasDocument()) {
            return handler.get("documentMessageHandler");
        }

        throw new PollerBotException("Unknown message type");
    }
}
