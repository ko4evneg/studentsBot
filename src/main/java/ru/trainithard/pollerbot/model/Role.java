package ru.trainithard.pollerbot.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.trainithard.pollerbot.service.command.CommandName;

@Getter
@RequiredArgsConstructor
public enum Role {
    NEW(NewUserSession.class, CommandName.NO_COMMAND),
    USER(RegularUserSession.class, CommandName.USER_GET_MENU);

    private final Class<? extends Session> sessionClass;
    private final CommandName startCommand;
}
