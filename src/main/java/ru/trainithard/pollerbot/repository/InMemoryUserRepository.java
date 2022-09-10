package ru.trainithard.pollerbot.repository;

import org.springframework.stereotype.Component;
import ru.trainithard.pollerbot.model.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class InMemoryUserRepository implements UserRepository {
    private final Map<Long, User> users = new HashMap<>();

    @Override
    public Optional<User> get(Long id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public Collection<User> getAll() {
        return users.values();
    }

    @Override
    public User save(User user) {
        return users.put(user.getId(), user);
    }
}
