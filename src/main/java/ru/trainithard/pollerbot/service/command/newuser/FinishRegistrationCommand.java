package ru.trainithard.pollerbot.service.command.newuser;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.trainithard.pollerbot.model.Role;
import ru.trainithard.pollerbot.model.Session;
import ru.trainithard.pollerbot.service.NotificationService;
import ru.trainithard.pollerbot.service.command.AbstractCommand;
import ru.trainithard.pollerbot.service.command.CommandName;
import ru.trainithard.pollerbot.service.component.SessionRetriever;
import ru.trainithard.pollerbot.service.dto.UserMessage;
import ru.trainithard.pollerbot.util.MessageConstructor;
import ru.trainithard.pollerbot.util.StringMetaDataManager;

import java.util.List;

import static ru.trainithard.pollerbot.service.command.CommandName.*;

@Component
@RequiredArgsConstructor
public class FinishRegistrationCommand extends AbstractCommand {
    private final SessionRetriever sessionRetriever;
    private final NotificationService notificationService;
    private final StringMetaDataManager metaDataManager;

    @Override
    public BotApiMethodMessage execute(UserMessage userMessage) {
        getUser(userMessage).setRole(Role.AUTH_PENDING);
        saveUser(userMessage);
        Session newSession = sessionRetriever.getNew(getUserId(userMessage));
        userMessage.setSession(newSession);
        saveSession(userMessage);
        notificationService.notifyAdmins(getSendMessage(userMessage));
        return getTextMessage(userMessage);
    }

    private SendMessage getSendMessage(UserMessage userMessage) {
        String text = String.format("User %s %s (%s; %s) requests authorization.", userMessage.getFirstName(),
                userMessage.getLastName(), userMessage. getNickName(), userMessage.getEmail());
        return messageConstructor.constructTextButtons(userMessage, text, getButtons(userMessage.getEmail()));
    }

    private List<List<MessageConstructor.Button>> getButtons(String email) {
        String callbackData = metaDataManager.addMetaData(AUTHORIZE_USER.toString(), "mail", email);
        return List.of(List.of(new MessageConstructor.Button("Authorize", callbackData),
                new MessageConstructor.Button("Later", ADMIN_GET_MENU)));
    }

    @Override
    public CommandName getCommandName() {
        return FINISH_REGISTRATION;
    }
}
