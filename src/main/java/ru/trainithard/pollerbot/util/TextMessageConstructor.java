package ru.trainithard.pollerbot.util;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.trainithard.pollerbot.exception.PollerBotException;

public class TextMessageConstructor implements MessageConstructor {
    @Override
    public SendMessage construct(Long chatId, String text) {
        return SendMessage.builder()
                .text(text)
                .chatId(chatId)
                .build();
    }

    @Override
    public SendMessage constructError(Long chatId, PollerBotException exception) {
        return construct(chatId, exception.getMessage());
    }
}
