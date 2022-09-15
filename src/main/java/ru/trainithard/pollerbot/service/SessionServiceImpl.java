package ru.trainithard.pollerbot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.trainithard.pollerbot.model.Role;
import ru.trainithard.pollerbot.model.Session;
import ru.trainithard.pollerbot.model.User;
import ru.trainithard.pollerbot.repository.SessionRepository;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {
    private final SessionRepository repository;

    @Override
    public Session get(User user) {
        return repository.get(user)
                .orElse(new Session(Role.NEW));
    }

    @Override
    public void save(User user, Session session) {
        repository.save(user, session);
    }
}
