package ru.trainithard.pollerbot.service.command.adminuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.trainithard.pollerbot.service.NotificationService;
import ru.trainithard.pollerbot.service.command.AbstractCommand;
import ru.trainithard.pollerbot.service.command.CommandName;
import ru.trainithard.pollerbot.service.dto.UserMessage;

import static ru.trainithard.pollerbot.service.command.CommandName.ADMIN_GET_MENU;
import static ru.trainithard.pollerbot.service.command.CommandName.NOTIFY_ALL;

@Component
public class NotifyAllCommand extends AbstractCommand {
    @Lazy
    @Autowired
    private NotificationService notificationService;

    @Override
    public BotApiMethodMessage execute(UserMessage userMessage) {
        try {
            notificationService.notifyAll(userMessage.getMessage());
            return getTextMessage(userMessage);
        } catch (TelegramApiException e) {
            return getErrorMessage(userMessage);
        } finally {
            getSession(userMessage).setNextCommandName(ADMIN_GET_MENU);
            saveSession(shiftSessionToThisCommand(userMessage));
        }
    }

    @Override
    public CommandName getCommandName() {
        return NOTIFY_ALL;
    }
}