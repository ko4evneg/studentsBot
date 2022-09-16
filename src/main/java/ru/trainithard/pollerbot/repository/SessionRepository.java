package ru.trainithard.pollerbot.repository;

import ru.trainithard.pollerbot.model.Session;

import java.util.Optional;

public interface SessionRepository {
    Optional<Session> get(Long userId);

    void save(Long userId, Session session);
}
