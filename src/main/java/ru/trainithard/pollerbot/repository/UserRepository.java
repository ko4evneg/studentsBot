package ru.trainithard.pollerbot.repository;

import ru.trainithard.pollerbot.model.Role;
import ru.trainithard.pollerbot.model.User;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> find(Long id);

    Collection<User> findAll();

    List<User> findByRole(Role role);

    User findByEmail(String email);

    User save(User user);

    List<Long> findAllChatIds();
}
