package ru.trainithard.pollerbot.service;

import ru.trainithard.pollerbot.model.User;

import java.util.List;

public interface UserService {
    User get(Long userId);

    List<User> getAll();

    User save(User user);
}
