package ru.trainithard.pollerbot.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MessageKeyboard {
    private String[] messages;
    private int[] buttonInRowsMarkup;
    private String[] buttonRows;

    public MessageKeyboard(String[] messages) {
        this.messages = messages;
    }

    public String getMessageOne() {
        return messages[0];
    }

    public String getMessageTwo() {
        return messages[1];
    }

    public boolean hasButtons() {
        return buttonRows != null && buttonInRowsMarkup != null;
    }
}
