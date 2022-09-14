package ru.trainithard.pollerbot.service.command;

import org.springframework.beans.factory.annotation.Autowired;
import ru.trainithard.pollerbot.service.SessionService;
import ru.trainithard.pollerbot.service.UserService;
import ru.trainithard.pollerbot.service.dto.UpdateUserSession;
import ru.trainithard.pollerbot.util.MessageConstructor;

public abstract class AbstractCommand implements Command {
    @Autowired
    protected MessageConstructor messageConstructor;
    @Autowired
    protected UserService userService;
    @Autowired
    protected SessionService sessionService;
    protected int stepNumber;

    protected void saveSession(UpdateUserSession updateUserSession) {
        sessionService.save(updateUserSession.getUser(), updateUserSession.getSession());
    }

    @Override
    public String getSessionClassName() {
        return "";
    }

    @Override
    public int getStepNumber() {
        return stepNumber;
    }
}
