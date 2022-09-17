package ru.trainithard.pollerbot.service.component;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.trainithard.pollerbot.model.Role;
import ru.trainithard.pollerbot.model.Session;
import ru.trainithard.pollerbot.service.SessionService;
import ru.trainithard.pollerbot.service.UserService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SessionRetriever {
    public static final int SESSION_TIMEOUT_MINUTES = 7;
    private final SessionService sessionService;
    private final UserService userService;

    public Session find(Long userId) {
        Optional<Session> sessionOptional = sessionService.find(userId);
        if (sessionOptional.isPresent() && isActive(sessionOptional.get())) {
            return refreshSessionAccessTime(sessionOptional);
        }
        return getNew(userId);
    }

    private Session refreshSessionAccessTime(Optional<Session> sessionOptional) {
        Session session = sessionOptional.get();
        session.setLastAccessTime(LocalDateTime.now());
        return session;
    }

    private boolean isActive(Session session) {
        return ChronoUnit.MINUTES.between(session.getLastAccessTime(), LocalDateTime.now()) < SESSION_TIMEOUT_MINUTES;
    }

    public Session getNew(Long userId) {
        return new Session(getUserRole(userId));
    }

    private Role getUserRole(Long userId) {
        return userService.find(userId).getRole();
    }
}
