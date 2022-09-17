package ru.trainithard.pollerbot.repository;

import org.springframework.stereotype.Component;
import ru.trainithard.pollerbot.model.Session;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class InMemorySessionRepository implements SessionRepository {
    private final Map<Long, Session> userStates = new HashMap<>();

    @Override
    public Optional<Session> find(Long userId) {
        return Optional.ofNullable(userStates.get(userId));
    }

    @Override
    public void save(Long userId, Session session) {
        userStates.put(userId, session);
    }

}
