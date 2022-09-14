package ru.trainithard.pollerbot.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

import static ru.trainithard.pollerbot.service.command.CommandName.*;

@Component
@Scope(value = "prototype")
public class NewUserSession extends AbstractSession {
    @PostConstruct
    public void init() {
        sessionCommands = List.of(
                commands.get(NEW_USER_INIT_COMMAND),
                commands.get(NEW_USER_START_REGISTRATION),
                commands.get(NEW_USER_ACCEPT_REGISTRATION_NAMES),
                commands.get(NEW_USER_ACCEPT_REGISTRATION_EMAILS)
        );
    }
}
