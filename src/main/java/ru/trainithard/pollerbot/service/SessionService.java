package ru.trainithard.pollerbot.service;

import ru.trainithard.pollerbot.model.Session;

import java.util.Optional;

public interface SessionService {
    Optional<Session> get(Long userId);

    void save(Long userId, Session session);
}
