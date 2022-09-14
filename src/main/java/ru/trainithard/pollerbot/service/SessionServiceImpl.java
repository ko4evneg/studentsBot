package ru.trainithard.pollerbot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.trainithard.pollerbot.model.Session;
import ru.trainithard.pollerbot.model.User;
import ru.trainithard.pollerbot.repository.SessionRepository;
import ru.trainithard.pollerbot.service.factory.SessionFactory;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {
    private final SessionRepository repository;
    private final SessionFactory factory;

    @Override
    public Session get(User user) {
        return repository.get(user)
                .orElse(factory.getNewUserSession());
    }

    @Override
    public void save(User user, Session session) {
        repository.save(user, session);
    }
}
