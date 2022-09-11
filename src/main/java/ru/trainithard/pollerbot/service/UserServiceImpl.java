package ru.trainithard.pollerbot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.trainithard.pollerbot.model.Role;
import ru.trainithard.pollerbot.model.User;
import ru.trainithard.pollerbot.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Override
    public User get(Long userId) {
        return repository.get(userId)
                .orElse(new User(userId, Role.NEW));
    }

    @Override
    public List<User> getAll() {
        return repository.getAll()
                .stream().toList();
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }

}
