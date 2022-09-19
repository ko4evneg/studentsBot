package ru.trainithard.pollerbot.service;

import ru.trainithard.pollerbot.model.Role;
import ru.trainithard.pollerbot.model.User;

import java.util.List;

public interface UserService {
    User find(Long userId);

    List<User> findAll();

    List<User> findByRole(Role role);

    User findByEmail(String email);

    List<Long> findAllChatIds();

    void save(User user);
}
