package ru.trainithard.pollerbot.repository;

import org.springframework.stereotype.Component;
import ru.trainithard.pollerbot.model.State;
import ru.trainithard.pollerbot.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class InMemoryStateRepository implements StateRepository {
    private final Map<User, State> userStates = new HashMap<>();

    @Override
    public Optional<State> get(User user) {
        return Optional.ofNullable(userStates.get(user));
    }

}
