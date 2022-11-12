package ru.trainithard.pollerbot.service.dto;

import lombok.Getter;
import lombok.Setter;
import ru.trainithard.pollerbot.exception.PollerBotException;

@Getter
@Setter
public class MessageKeyboard {
    public static final int STANDARD_MESSAGES_END_INDEX = 1;
    private String message;
    private String errorMessage;
    /**
     * Markup: each array element represents row. The number in element represents buttons count in that row.
     */
    private int[] buttonInRowsMarkup;
    private String[] buttonRows;

    public MessageKeyboard(String...messages) {
        if (messages.length > 2) {
            throw new PollerBotException("Wrong arguments count for creating keyboard.");
        }
        if (messages.length == 1) {
            this.message = messages[0];
        }
        if (messages.length == 2) {
            this.errorMessage = messages[1];
        }
    }

    public MessageKeyboard(String[] messages, int[] buttonInRowsMarkup, String[] buttonRows) {
        this(messages);
        this.buttonInRowsMarkup = buttonInRowsMarkup;
        this.buttonRows = buttonRows;
    }

    public String getMessage() {
        return message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean hasButtons() {
        return buttonRows != null && buttonInRowsMarkup != null;
    }
}
