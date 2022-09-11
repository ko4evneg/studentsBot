package ru.trainithard.pollerbot.repository;

import ru.trainithard.pollerbot.model.Session;
import ru.trainithard.pollerbot.model.User;

import java.util.Optional;

public interface SessionRepository {
    Optional<Session> get(User user);

    void save(User user, Session session);
}
