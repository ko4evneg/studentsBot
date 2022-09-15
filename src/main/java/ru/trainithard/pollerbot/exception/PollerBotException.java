package ru.trainithard.pollerbot.exception;

public class PollerBotException extends RuntimeException{
    public PollerBotException(String message) {
        super(message);
    }

    public PollerBotException(String message, Throwable cause) {
        super(message, cause);
    }
}
