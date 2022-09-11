package ru.trainithard.pollerbot.repository;

import org.springframework.stereotype.Component;
import ru.trainithard.pollerbot.model.Session;
import ru.trainithard.pollerbot.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class InMemorySessionRepository implements SessionRepository {
    private final Map<User, Session> userStates = new HashMap<>();

    @Override
    public Optional<Session> get(User user) {
        return Optional.ofNullable(userStates.get(user));
    }

    @Override
    public void save(User user, Session session) {
        userStates.put(user, session);
    }

}
