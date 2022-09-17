package ru.trainithard.pollerbot.service.command;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Optional;

@Getter
@NoArgsConstructor
public enum CommandName {
    NO_COMMAND,
    REGISTER_NAMES,
    REGISTER_EMAIL,
    FINISH_REGISTRATION,

    USER_GET_MENU,
    USER_GET_DATA,
    LESSONS_MENU,
    RECENT_LESSONS,
    SEARCH_LESSON_BY_NUMBER,
    SEARCH_LESSON_BY_KEYWORD,
    SHOW_ALL_KEYWORDS,
    USER_UPLOAD_HOMEWORK,
    FINISH_UPLOAD_HOMEWORK,

    ADMIN_GET_MENU,
    NOTIFY_ALL,
    CONSTRUCT_NOTIFICATION,

    HELP("/help"),
    RESET_SESSION("/reset"),
    ELEVATE("/elevate"),
    DELEVATE("/delevate");

    CommandName(String name) {
        this.name = name;
    }

    private String name;

    public static Optional<CommandName> getByName(String name) {
        return Arrays.stream(values())
                .filter(commandName -> name.equals(commandName.getName()) || name.equals(commandName.toString()))
                .findFirst();
    }
}
