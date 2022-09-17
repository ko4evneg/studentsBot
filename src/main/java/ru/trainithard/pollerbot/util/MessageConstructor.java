package ru.trainithard.pollerbot.util;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.trainithard.pollerbot.exception.PollerBotException;
import ru.trainithard.pollerbot.service.command.CommandName;
import ru.trainithard.pollerbot.service.component.ButtonVersionEnricher;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MessageConstructor {
    private final ButtonVersionEnricher buttonVersionEnricher;

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

    public SendMessage constructTextButtons(Long chatId, String text, List<List<Button>> buttons) {
        SendMessage reply = construct(chatId, text);
        reply.setReplyMarkup(createKeyboard(buttons));
        return reply;
    }

    private InlineKeyboardMarkup createKeyboard(List<List<Button>> buttons) {
        List<List<InlineKeyboardButton>> buttonLines = buttons.stream()
                .map(this::createButtonsRow)
                .toList();
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
                .callbackData(button.commandName().toString() + "___" + button.version())
                .build();
    }

    public List<List<MessageConstructor.Button>> getEnrichedVersionButtons(List<List<MessageConstructor.Button>> buttons, long sessionVersion) {
        return buttonVersionEnricher.enrich(buttons, sessionVersion);
    }

    public SendMessage constructError(Long chatId, PollerBotException exception) {
        return construct(chatId, exception.getMessage());
    }

    @Setter
    public static class Button {
        private String text;
        private CommandName commandName;
        private long version;

        public Button(String text, CommandName commandName) {
            this.text = text;
            this.commandName = commandName;
        }

        public String text() {
            return text;
        }

        public CommandName commandName() {
            return commandName;
        }

        public long version() {
            return version;
        }
    }
}
