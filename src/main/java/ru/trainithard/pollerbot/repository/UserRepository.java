package ru.trainithard.pollerbot.repository;

import ru.trainithard.pollerbot.model.Role;
import ru.trainithard.pollerbot.model.User;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> get(Long id);

    Collection<User> getAll();

    List<User> getByRole(Role role);

    User save(User user);

    List<Long> getAllChatIds();
}
