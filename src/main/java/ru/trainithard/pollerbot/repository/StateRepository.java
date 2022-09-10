package ru.trainithard.pollerbot.repository;

import ru.trainithard.pollerbot.model.State;
import ru.trainithard.pollerbot.model.User;

import java.util.Optional;

public interface StateRepository {
    Optional<State> get(User user);

    void save(User user, State state);
}
