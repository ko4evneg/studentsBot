package ru.trainithard.pollerbot.service.component;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.trainithard.pollerbot.model.Role;
import ru.trainithard.pollerbot.model.Session;
import ru.trainithard.pollerbot.service.SessionService;
import ru.trainithard.pollerbot.service.UserService;

@Component
@RequiredArgsConstructor
public class SessionFinder {
    private final SessionService sessionService;
    private final UserService userService;

    public Session find(Long userId) {
        return sessionService.get(userId)
                .orElse(new Session(getUserRole(userId)));
    }

    private Role getUserRole(Long userId) {
        return userService.get(userId).getRole();
    }
}
