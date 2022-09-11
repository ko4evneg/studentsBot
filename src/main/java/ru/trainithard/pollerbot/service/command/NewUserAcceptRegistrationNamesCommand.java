package ru.trainithard.pollerbot.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.trainithard.pollerbot.model.Session;
import ru.trainithard.pollerbot.model.User;
import ru.trainithard.pollerbot.service.dto.UpdateUserSession;
import ru.trainithard.pollerbot.service.validator.UserNamesValidator;

@Component
@RequiredArgsConstructor
public class NewUserAcceptRegistrationNamesCommand extends AbstractCommand {
    private final UserNamesValidator validator;

    @Override
    public SendMessage execute(UpdateUserSession updateUserSession) {
        if (validator.validate(getMessage(updateUserSession))) {
            saveUserNames(updateUserSession);

            updateUserSession.getSession().stepForward();
            saveSession(updateUserSession);

            return messageConstructor.construct(getChatId(updateUserSession), "Введите email для связи:");
        }
        return messageConstructor.construct(getChatId(updateUserSession), "Введены некорректные данные, попробуйте ввести еще раз:");
    }

    private void saveUserNames(UpdateUserSession updateUserSession) {
        User user = updateUserSession.getUser();
        String firstName = getMessage(updateUserSession).split("\\s")[0];
        String lastName = getMessage(updateUserSession).split("\\s")[0];

        user.setFirstName(firstName);
        user.setLastName(lastName);

        userService.save(user);
    }

    @Override
    public CommandName getCommandName() {
        return CommandName.NEW_USER_ACCEPT_REGISTRATION_NAMES;
    }
}
