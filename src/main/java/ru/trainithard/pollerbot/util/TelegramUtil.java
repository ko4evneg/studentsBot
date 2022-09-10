package ru.trainithard.pollerbot.util;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

@Component
public class TelegramUtil {
    public InlineKeyboardButton getButton(String text, String callbackData) {
        return InlineKeyboardButton.builder()
                .text(text)
                .callbackData(callbackData)
                .build();
    }

    public InlineKeyboardMarkup getKeyboard(InlineKeyboardButton...buttons) {
        return InlineKeyboardMarkup.builder()
                .keyboard(List.of(List.of(buttons)))
                .build();
    }
}
