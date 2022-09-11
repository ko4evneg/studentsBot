package ru.trainithard.pollerbot.util;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.trainithard.pollerbot.exception.PollerBotException;
import ru.trainithard.pollerbot.service.command.CommandName;

import java.util.List;

@Component
public class MessageConstructor {
    public SendMessage construct(Long chatId, String text) {
        return SendMessage.builder()
                .text(text)
                .chatId(chatId)
                .build();
    }

    public SendMessage constructError(Long chatId, PollerBotException exception) {
        return construct(chatId, exception.getMessage());
    }

    public SendMessage constructTextButtons(Long chatId, String text, List<List<Button>> buttons) {
        InlineKeyboardMarkup keyboard = createKeyboard(buttons);
        SendMessage reply = construct(chatId, text);
        reply.setReplyMarkup(keyboard);
        return reply;
    }

    private InlineKeyboardMarkup createKeyboard(List<List<Button>> buttons) {
        List<List<InlineKeyboardButton>> buttonLines = buttons.stream().map(this::createButtonsRow).toList();
        return InlineKeyboardMarkup.builder()
                .keyboard(buttonLines)
                .build();
    }

    private List<InlineKeyboardButton> createButtonsRow(List<Button> buttons) {
        return buttons.stream().map(this::createButton).toList();
    }

    private InlineKeyboardButton createButton(Button button) {
        return InlineKeyboardButton.builder()
                .text(button.text())
                .callbackData(button.commandName().toString())
                .build();
    }

    public record Button(String text, CommandName commandName) {
    }
}
