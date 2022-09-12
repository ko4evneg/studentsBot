package ru.trainithard.pollerbot.service;

import ru.trainithard.pollerbot.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> get(Long userId);

    List<User> getAll();

    List<Long> getAllChatIds();

    User save(User user);
}
