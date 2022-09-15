package ru.trainithard.pollerbot.model;

import org.springframework.stereotype.Component;

@Component
public class NewUserSession extends Session {
    @Override
    public Role getRole() {
        return Role.NEW;
    }
}
