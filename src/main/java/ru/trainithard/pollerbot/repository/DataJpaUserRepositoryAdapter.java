package ru.trainithard.pollerbot.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.trainithard.pollerbot.model.Role;
import ru.trainithard.pollerbot.model.User;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DataJpaUserRepositoryAdapter implements UserRepository {
    private final DataJpaUserRepository repository;

    @Override
    public Optional<User> get(Long id) {
        return repository.findById(id);
    }

    @Override
    public Collection<User> getAll() {
        return repository.findAll();
    }

    @Override
    public List<User> getByRole(Role role) {
        return repository.findByRole(role);
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public List<Long> getAllChatIds() {
        return repository.findAllChatIds();
    }
}
