package ru.trainithard.pollerbot.service.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.trainithard.pollerbot.service.SessionService;
import ru.trainithard.pollerbot.service.UserService;
import ru.trainithard.pollerbot.service.dto.UpdateUserSession;
import ru.trainithard.pollerbot.util.TextButtonMessageConstructor;
import ru.trainithard.pollerbot.util.TextMessageConstructor;

public abstract class AbstractCommand implements Command {
    @Autowired
    protected TextMessageConstructor textMessageConstructor;
    @Autowired
    protected TextButtonMessageConstructor textButtonMessageConstructor;
    @Autowired
    protected UserService userService;
    @Autowired
    protected SessionService sessionService;

    protected Long getChatId(UpdateUserSession updateUserSession) {
        Update update = updateUserSession.getUpdate();
        if (update.hasMessage()) {
            return update.getMessage().getChatId();
        }

        return update.getCallbackQuery().getMessage().getChatId();

        //todo polls
    }

    protected String getMessage(UpdateUserSession updateUserSession) {
        Update update = updateUserSession.getUpdate();
        if (update.hasMessage() && update.getMessage().hasText()) {
            return update.getMessage().getText();
        }
        return "";
    }

    protected void saveSession(UpdateUserSession updateUserSession) {
        sessionService.save(updateUserSession.getUser(), updateUserSession.getSession());
    }
}
