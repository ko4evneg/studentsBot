package ru.trainithard.pollerbot.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Scope(value = "prototype")
public class RegularUserSession extends AbstractSession {
    @PostConstruct
    public void init() {
        newSession = true;
        commandIndex = 0;
        commands.addAll(sessionCommands.get(getClass().getSimpleName()));
    }
}
