package ru.trainithard.pollerbot.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.trainithard.pollerbot.model.Session;
import ru.trainithard.pollerbot.model.User;

@Getter
@Setter
@Builder
public class UserMessage {
    private Update update;
    private User user;
    private Session session;

    public Long getChatId() {
        return user.getChatId();
    }

    public String getMessage() {
        return update.getMessage().getText();
    }

    public String getCallbackData() {
        return update.getCallbackQuery().getData();
    }
}
