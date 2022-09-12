package ru.trainithard.pollerbot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.trainithard.pollerbot.model.User;
import ru.trainithard.pollerbot.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Override
    public Optional<User> get(Long userId) {
        return repository.get(userId);

    }

    @Override
    public List<User> getAll() {
        return repository.getAll()
                .stream().toList();
    }

    @Override
    public List<Long> getAllChatIds(){
        return repository.getAllChatIds();
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }

}
