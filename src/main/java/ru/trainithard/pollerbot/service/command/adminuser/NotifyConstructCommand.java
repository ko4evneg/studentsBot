package ru.trainithard.pollerbot.service.command.adminuser;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import ru.trainithard.pollerbot.service.command.AbstractCommand;
import ru.trainithard.pollerbot.service.command.CommandName;
import ru.trainithard.pollerbot.service.dto.UserMessage;

import static ru.trainithard.pollerbot.service.command.CommandName.CONSTRUCT_NOTIFICATION;

@Component
@RequiredArgsConstructor
public class NotifyConstructCommand extends AbstractCommand {
    private final NotifyAllCommand notifyAllCommand;

    @Override
    public BotApiMethodMessage execute(UserMessage userMessage) {
        if (isFirstInvocation(userMessage)) {
            getSession(userMessage).setPreviousCommandName(getCommandName());
            saveSession(userMessage);
            return getTextMessage(userMessage);
        }

        userMessage.getSession().setNextCommandName(CommandName.NOTIFY_ALL);
        return notifyAllCommand.execute(userMessage);
    }

    @Override
    public CommandName getCommandName() {
        return CONSTRUCT_NOTIFICATION;
    }
}
