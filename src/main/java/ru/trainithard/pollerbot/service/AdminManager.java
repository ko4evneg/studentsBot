package ru.trainithard.pollerbot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.trainithard.pollerbot.model.User;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminManager {
    private final UserService userService;

    public List<User> getUsers() {
        return userService.findAll();
    }
}
