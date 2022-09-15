package ru.trainithard.pollerbot.model;

import org.springframework.stereotype.Component;

@Component
public class RegularUserSession extends Session {
    @Override
    public Role getRole() {
        return Role.USER;
    }
}
