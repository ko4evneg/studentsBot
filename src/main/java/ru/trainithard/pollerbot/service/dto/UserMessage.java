package ru.trainithard.pollerbot.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.trainithard.pollerbot.model.Session;
import ru.trainithard.pollerbot.model.User;
import ru.trainithard.pollerbot.service.command.CommandName;

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

    public Long getUserId() {
        return user.getId();
    }

    public String getFirstName() {
        return user.getFirstName() == null ? "" : user.getFirstName();
    }

    public String getLastName() {
        return user.getLastName() == null ? "" : user.getLastName();
    }

    public String getNickName() {
        return user.getNickName() == null ? "" : user.getNickName();
    }

    public String getEmail() {
        return user.getEmail();
    }

    public String getEmailInFileFormat() {
        String email = getEmail().replace("@", "_");
        return email.replaceAll("\\.\\D+$", "");
    }

    public String getMessage() {
        return update.getMessage().getText();
    }

    public String getCallbackData() {
        return update.getCallbackQuery().getData();
    }

    public boolean hasFile(){
        return getUpdate().getMessage().hasDocument();
    }

    public Document getFile() {
        return getUpdate().getMessage().getDocument();
    }

    public void setNextCommandName(CommandName nextCommandName) {
        session.setNextCommandName(nextCommandName);
    }

    public void setPreviousCommandName(CommandName previousCommandName) {
        session.setPreviousCommandName(previousCommandName);
    }

    public void setPreviousNextCommandNames(CommandName previousCommandName, CommandName nextCommandName) {
        session.setPreviousNextCommandNames(previousCommandName, nextCommandName);
    }

    public long getSessionVersion() {
        return session.getVersion();
    }

    public CommandName getStartCommand() {
        return user.getRole().getStartCommand();
    }
}
