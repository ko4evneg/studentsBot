package ru.trainithard.pollerbot.service.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum CommandName{
    NO_COMMAND(),
    HELP("/help"),
    RESET("/reset"),
    POLL_ANSWER_HANDLER,
    INPUT_HANDLER,
    REGISTER,
    NEW_USER_INIT_COMMAND(),
    NEW_USER_START_REGISTRATION(),
    NEW_USER_ACCEPT_REGISTRATION_NAMES(),
    NEW_USER_ACCEPT_REGISTRATION_EMAILS(),
    USER_GET_MENU(),
    USER_GET_DATA(),
    USER_GET_LESSONS()
    ;

    private String name;

    public static CommandName getByName(String name) {
        return Arrays.stream(values())
                .filter(commandName -> name.equals(commandName.getName()) || name.equals(commandName.name))
                .findFirst()
                .orElse(NO_COMMAND);
    }
}
