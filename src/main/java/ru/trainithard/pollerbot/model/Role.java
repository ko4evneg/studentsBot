package ru.trainithard.pollerbot.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.trainithard.pollerbot.service.command.CommandName;

@Getter
@RequiredArgsConstructor
public enum Role {
    NEW(CommandName.NO_COMMAND),
    AUTH_PENDING(CommandName.AUTH_PENDING),
    USER(CommandName.USER_GET_MENU),
    ADMIN(CommandName.ADMIN_GET_MENU);

    private final CommandName startCommand;
}
