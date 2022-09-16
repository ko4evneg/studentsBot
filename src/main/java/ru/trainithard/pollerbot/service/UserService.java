package ru.trainithard.pollerbot.service;

import ru.trainithard.pollerbot.model.Role;
import ru.trainithard.pollerbot.model.User;

import java.util.List;

public interface UserService {
    User get(Long userId);

    List<User> getAll();

    List<User> getByRole(Role role);

    List<Long> getAllChatIds();

    void save(User user);
}
