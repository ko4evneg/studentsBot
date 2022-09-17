package ru.trainithard.pollerbot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.trainithard.pollerbot.model.Session;
import ru.trainithard.pollerbot.repository.SessionRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {
    private final SessionRepository repository;

    @Override
    public Optional<Session> find(Long userId) {
        return repository.get(userId);
    }

    @Override
    public void save(Long userId, Session session) {
        repository.save(userId, session);
    }
}
