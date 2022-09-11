package ru.trainithard.pollerbot.service;

import ru.trainithard.pollerbot.model.Session;
import ru.trainithard.pollerbot.model.User;

public interface SessionService {
    Session get(User user);

    void save(User user, Session session);
}
