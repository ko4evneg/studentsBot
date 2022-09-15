package ru.trainithard.pollerbot.service.factory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.trainithard.pollerbot.model.Session;
import ru.trainithard.pollerbot.model.User;

@Component
@RequiredArgsConstructor
public class SessionFactory {
    public Session getNewSession(User user) throws ReflectiveOperationException {
        return getSessionClass(user).getConstructor().newInstance();
    }

    private Class<? extends Session> getSessionClass(User user) {
        return user.getRole().getSessionClass();
    }
}
