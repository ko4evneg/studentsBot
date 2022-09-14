package ru.trainithard.pollerbot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.trainithard.pollerbot.model.Role;
import ru.trainithard.pollerbot.model.Session;
import ru.trainithard.pollerbot.model.User;
import ru.trainithard.pollerbot.service.component.CommandFinder;
import ru.trainithard.pollerbot.service.component.UpdateParser;
import ru.trainithard.pollerbot.service.dto.UpdateUserSession;

@Service
@RequiredArgsConstructor
public class UpdateProcessingManager {
    private final UpdateParser updateParser;
    private final UserServiceImpl userService;
    private final SessionService sessionService;
    private final CommandFinder commandFinder;

    public SendMessage process(Update update) {
        UpdateUserSession updateUserSession = getUpdateUserSession(update);

        if (!commandFinder.hasCommand(update)) {
            Session currentSession = updateUserSession.getSession();
            return currentSession.getCurrentCommandName().execute(updateUserSession);
        }

        return commandFinder.find(update).execute(updateUserSession);
    }

    private UpdateUserSession getUpdateUserSession(Update update) {
        Long userId = updateParser.getUserId(update);
        User user = userService.get(userId).
                orElse(new User(userId, Role.NEW, getChatId(update)));
        Session session = sessionService.get(user);
        return new UpdateUserSession(update, user, session);
    }

    private Long getChatId(Update update) {
        if (update.hasMessage()) {
            return update.getMessage().getChatId();
        }
        return update.getCallbackQuery().getMessage().getChatId();
        //todo polls
    }
}
