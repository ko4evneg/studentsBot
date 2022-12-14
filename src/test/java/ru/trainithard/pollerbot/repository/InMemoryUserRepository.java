package ru.trainithard.pollerbot.repository;

import ru.trainithard.pollerbot.model.Role;
import ru.trainithard.pollerbot.model.User;

import java.util.*;

public class InMemoryUserRepository implements UserRepository {
    private final Map<Long, User> users = new HashMap<>();

    @Override
    public Optional<User> find(Long id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public Collection<User> findAll() {
        return users.values();
    }

    @Override
    public List<User> findByRole(Role role) {
        return users.values().stream()
                .filter(user -> role.equals(user.getRole()))
                .toList();
    }

    @Override
    public User save(User user) {
        return users.put(user.getId(), user);
    }

    @Override
    public List<Long> findAllChatIds() {
        return users.values().stream()
                .map(User::getChatId)
                .toList();
    }
}
