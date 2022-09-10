package ru.trainithard.pollerbot.service;

import ru.trainithard.pollerbot.model.State;
import ru.trainithard.pollerbot.model.User;

public interface StateService {
    State get(User user);

    void save(User user, State state);
}
