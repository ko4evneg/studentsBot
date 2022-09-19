package ru.trainithard.pollerbot.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MessageKeyboard {
    public static final int STANDARD_MESSAGES_END_INDEX = 1;
    private String[] messages;
    private int[] buttonInRowsMarkup;
    private String[] buttonRows;

    public MessageKeyboard(String[] messages) {
        this.messages = messages;
    }

    public String getMessage() {
        return messages[0];
    }

    public String getErrorMessage() {
        return messages[1];
    }

    public String getCustomMessage(int number) {
        return messages[STANDARD_MESSAGES_END_INDEX + number];
    }

    public boolean hasButtons() {
        return buttonRows != null && buttonInRowsMarkup != null;
    }
}
