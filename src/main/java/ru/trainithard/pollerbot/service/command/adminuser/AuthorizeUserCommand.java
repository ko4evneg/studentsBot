package ru.trainithard.pollerbot.service.command.adminuser;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import ru.trainithard.pollerbot.model.Role;
import ru.trainithard.pollerbot.model.User;
import ru.trainithard.pollerbot.service.NotificationService;
import ru.trainithard.pollerbot.service.UserService;
import ru.trainithard.pollerbot.service.command.AbstractCommand;
import ru.trainithard.pollerbot.service.command.CommandName;
import ru.trainithard.pollerbot.service.dto.UserMessage;
import ru.trainithard.pollerbot.util.StringMetaDataManager;

@Component
@RequiredArgsConstructor
public class AuthorizeUserCommand extends AbstractCommand {
    private final UserService userService;
    private final StringMetaDataManager metaDataManager;
    private final NotificationService notificationService;

    @Override
    public BotApiMethodMessage execute(UserMessage userMessage) {
        User user = userService.findByEmail(getEmail(userMessage));
        user.setRole(Role.USER);
        saveUser(userMessage);
        notificationService.notifyUser(user.getChatId(), );
        return getTextMessage(userMessage);
    }

    private String getEmail(UserMessage userMessage) {
        return metaDataManager.getMetaDataValue(userMessage.getCallbackData(), "mail");
    }

    @Override
    public CommandName getCommandName() {
        return CommandName.AUTHORIZE_USER;
    }
}
