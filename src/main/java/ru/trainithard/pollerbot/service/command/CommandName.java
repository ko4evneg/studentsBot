package ru.trainithard.pollerbot.service.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommandName{
    NEW_USER_INIT_COMMAND("NEW_USER_INIT_COMMAND"),
    NEW_USER_START_REGISTRATION("NEW_USER_START_REGISTRATION"),
    NEW_USER_ACCEPT_REGISTRATION_NAMES("NEW_USER_ACCEPT_REGISTRATION_NAMES"),
    NEW_USER_ACCEPT_REGISTRATION_EMAILS("NEW_USER_ACCEPT_REGISTRATION_EMAILS"),
    USER_GET_MENU("USER_GET_MENU"),
    USER_GET_DATA("USER_GET_DATA"),
    USER_GET_LESSONS("USER_GET_LESSONS")
    ;

    private final String humanName;

}
