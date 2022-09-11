package ru.trainithard.pollerbot.model;

import org.springframework.stereotype.Component;

import java.util.List;

import static ru.trainithard.pollerbot.service.command.CommandName.*;

@Component
public class NewUserSession extends AbstractSession {
    public NewUserSession() {
        newSession = true;
        commandIndex = 0;
        commandNames.addAll(List.of(
                NEW_USER_INIT_COMMAND,
                NEW_USER_ACCEPT_REGISTRATION_NAMES,
                NEW_USER_ACCEPT_REGISTRATION_EMAILS
        ));
    }
}
