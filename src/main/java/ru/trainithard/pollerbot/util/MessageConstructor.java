package ru.trainithard.pollerbot.util;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.trainithard.pollerbot.exception.PollerBotException;
import ru.trainithard.pollerbot.service.command.CommandName;
import ru.trainithard.pollerbot.service.dto.MessageKeyboard;
import ru.trainithard.pollerbot.service.dto.UserMessage;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MessageConstructor {
    private final ButtonConstructor buttonConstructor;
    private final StringMetaDataManager metaDataManager;

    public SendMessage construct(Long chatId, String text) {
        return SendMessage.builder()
                .text(text)
                .chatId(chatId)
                .build();
    }

    public SendMessage constructText(Long chatId, String text) {
        return SendMessage.builder()
                .text(text)
                .chatId(chatId)
                .build();
    }

    public SendMessage constructTextButtons(UserMessage userMessage, MessageKeyboard messageKeyboard) {
        SendMessage reply = construct(userMessage.getChatId(), messageKeyboard.getMessage());
        if (messageKeyboard.hasButtons()) {
            List<List<Button>> buttons = buttonConstructor.constructButtons(messageKeyboard);
            reply.setReplyMarkup(createKeyboard(userMessage, buttons));
        }
        return reply;
    }

    public SendMessage constructErrorText(UserMessage userMessage, MessageKeyboard messageKeyboard) {
        return construct(userMessage.getChatId(), messageKeyboard.getErrorMessage());
    }

    public SendMessage constructCustomTextButtons(UserMessage userMessage, MessageKeyboard messageKeyboard,
                                                  int[] buttonsMarkup, String... buttons) {
        SendMessage reply = construct(userMessage.getChatId(), messageKeyboard.getMessage());
        messageKeyboard.setButtonInRowsMarkup(buttonsMarkup);
        messageKeyboard.setButtonRows(buttons);
        reply.setReplyMarkup(createKeyboard(userMessage, buttonConstructor.constructButtons(messageKeyboard)));
        return reply;
    }

    public SendMessage constructCustomTextButtons(UserMessage userMessage, MessageKeyboard messageKeyboard, String text) {
        SendMessage reply = construct(userMessage.getChatId(), text);
        if (messageKeyboard.hasButtons()) {
            List<List<Button>> buttons = buttonConstructor.constructButtons(messageKeyboard);
            reply.setReplyMarkup(createKeyboard(userMessage, buttons));
        }
        return reply;
    }

    private InlineKeyboardMarkup createKeyboard(UserMessage userMessage, List<List<Button>> buttons) {
        List<List<InlineKeyboardButton>> buttonLines = buttons.stream()
                .map(buttonsRow -> createButtonsRow(userMessage, buttonsRow))
                .toList();
        return InlineKeyboardMarkup.builder()
                .keyboard(buttonLines)
                .build();
    }

    private List<InlineKeyboardButton> createButtonsRow(UserMessage userMessage, List<Button> buttons) {
        return buttons.stream().map(button -> createButton(userMessage, button)).toList();
    }

    private InlineKeyboardButton createButton(UserMessage userMessage, Button button) {
        return InlineKeyboardButton.builder()
                .text(button.text())
                .callbackData(constructCommandWithVersionMetaData(userMessage, button))
                .build();
    }

    private String constructCommandWithVersionMetaData(UserMessage userMessage, Button button) {
        return metaDataManager.addMetaData(button.callbackData(), "v", userMessage.getSessionVersion());
    }

    @Setter
    public static class Button {
        private String text;
        private String callbackData;

        public Button(String text, CommandName callbackData) {
            this.text = text;
            this.callbackData = callbackData.toString();
        }

        public Button(String text, String callbackData) {
            this.text = text;
            this.callbackData = callbackData;
        }

        public String text() {
            return text;
        }

        public String callbackData() {
            return callbackData;
        }
    }
}
