package ru.trainithard.pollerbot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.trainithard.pollerbot.model.State;
import ru.trainithard.pollerbot.model.User;
import ru.trainithard.pollerbot.repository.StateRepository;

@Service
@RequiredArgsConstructor
public class StateServiceImpl implements StateService {
    private final StateRepository repository;

    @Override
    public State get(User user) {
        return repository.get(user)
                .orElse(State.NEW_USER_FIRST_REQUEST);
    }

    @Override
    public void save(User user, State state) {
        repository.save(user, state);
    }
}
