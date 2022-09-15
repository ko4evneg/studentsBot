package ru.trainithard.pollerbot.service.command;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@Getter
@NoArgsConstructor
public enum CommandName {
    NO_COMMAND,
    REGISTER_NAMES,
    REGISTER_EMAIL,
    FINISH_REGISTRATION,

    USER_GET_MENU,

    HELP("/help"),
    RESET_SESSION("/reset");


    CommandName(String name) {
        this.name = name;
    }

    private String name;

    public static CommandName getByName(String name) {
        return Arrays.stream(values())
                .filter(commandName -> name.equals(commandName.getName()) || name.equals(commandName.toString()))
                .findFirst()
                .orElse(NO_COMMAND);
    }
}
