package ru.trainithard.pollerbot.service.command.newuser;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import ru.trainithard.pollerbot.model.Role;
import ru.trainithard.pollerbot.model.Session;
import ru.trainithard.pollerbot.service.command.AbstractCommand;
import ru.trainithard.pollerbot.service.command.CommandName;
import ru.trainithard.pollerbot.service.component.SessionRetriever;
import ru.trainithard.pollerbot.service.dto.UserMessage;

import static ru.trainithard.pollerbot.service.command.CommandName.FINISH_REGISTRATION;

@Component
@RequiredArgsConstructor
public class FinishRegistrationCommand extends AbstractCommand {
    private final SessionRetriever sessionRetriever;

    @Override
    public BotApiMethodMessage execute(UserMessage userMessage) {
        getUser(userMessage).setRole(Role.USER);
        saveUser(userMessage);
        Session newSession = sessionRetriever.getNew(getUserId(userMessage));
        userMessage.setSession(newSession);
        saveSession(userMessage);
        return getTextButtonMessage(userMessage);
    }


    @Override
    public CommandName getCommandName() {
        return FINISH_REGISTRATION;
    }
}
