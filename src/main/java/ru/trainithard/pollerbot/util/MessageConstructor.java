package ru.trainithard.pollerbot.util;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.trainithard.pollerbot.exception.PollerBotException;

public interface MessageConstructor {
    SendMessage construct(Long chatId, String text);

    SendMessage constructError(Long chatId, PollerBotException exception);
}
