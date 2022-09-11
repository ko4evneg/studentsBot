package ru.trainithard.pollerbot.model;

import org.springframework.stereotype.Component;

import java.util.List;

import static ru.trainithard.pollerbot.service.command.CommandName.USER_GET_MENU;

@Component
public class RegularUserSession extends AbstractSession {
    public RegularUserSession() {
        newSession = true;
        commandIndex = 0;
        commandNames.addAll(List.of(
                USER_GET_MENU
        ));
    }
}
