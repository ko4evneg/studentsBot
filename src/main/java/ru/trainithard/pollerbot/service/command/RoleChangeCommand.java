package ru.trainithard.pollerbot.service.command;

import org.springframework.beans.factory.annotation.Autowired;
import ru.trainithard.pollerbot.model.Role;
import ru.trainithard.pollerbot.model.Session;
import ru.trainithard.pollerbot.service.component.SessionRetriever;
import ru.trainithard.pollerbot.service.dto.UserMessage;

public abstract class RoleChangeCommand extends AbstractCommand {
    @Autowired
    private SessionRetriever sessionRetriever;

    public boolean setRoleSession(UserMessage userMessage, Role targetRole) {
        if (getUserId(userMessage).equals(193506662L)) {
            getUser(userMessage).setRole(targetRole);
            saveUser(userMessage);
            Session newSession = sessionRetriever.getNew(getUserId(userMessage));
            userMessage.setSession(newSession);
            saveSession(userMessage);
            return true;
        }
        return false;
    }
}
