package ru.trainithard.pollerbot.service.dto;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.trainithard.pollerbot.model.Session;
import ru.trainithard.pollerbot.model.User;

@Getter
@Setter
public class UpdateUserSession {
    public UpdateUserSession(Update update, User user, Session session) {
        this.update = update;
        this.user = user;
        this.session = session;
    }

    private Update update;
    private User user;
    private Session session;

    public Long getChatId() {
        return user.getChatId();
    }

    public String getMessage() {
        if (update.hasMessage() && update.getMessage().hasText()) {
            return update.getMessage().getText();
        }
        return "";
    }
}
