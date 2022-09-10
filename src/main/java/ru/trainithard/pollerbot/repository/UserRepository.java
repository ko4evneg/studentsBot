package ru.trainithard.pollerbot.repository;

import ru.trainithard.pollerbot.model.User;

import java.util.Collection;
import java.util.Optional;

public interface UserRepository {
    Optional<User> get(Long id);

    Collection<User> getAll();

    User save(User user);
}
