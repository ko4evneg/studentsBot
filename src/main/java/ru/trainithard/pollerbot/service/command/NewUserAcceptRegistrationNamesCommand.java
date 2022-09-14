package ru.trainithard.pollerbot.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.trainithard.pollerbot.model.NewUserSession;
import ru.trainithard.pollerbot.model.Session;
import ru.trainithard.pollerbot.model.User;
import ru.trainithard.pollerbot.service.dto.UpdateUserSession;
import ru.trainithard.pollerbot.service.validator.UserNamesValidator;

@Component
@RequiredArgsConstructor
public class NewUserAcceptRegistrationNamesCommand extends AbstractCommand {
    private final UserNamesValidator validator;

    {
        stepNumber = 3;
    }

    @Override
    public SendMessage execute(UpdateUserSession updateUserSession) {
        if (validator.validate(updateUserSession.getMessage())) {
            saveUserNames(updateUserSession);

            updateUserSession.getSession().stepForward();
            saveSession(updateUserSession);

            return messageConstructor.construct(updateUserSession.getChatId(), "Введите email для связи:");
        }
        return messageConstructor.construct(updateUserSession.getChatId(), "Введены некорректные данные, попробуйте ввести еще раз:");
    }

    private void saveUserNames(UpdateUserSession updateUserSession) {
        User user = updateUserSession.getUser();
        String firstName = updateUserSession.getMessage().split("\\s")[0];
        String lastName = updateUserSession.getMessage().split("\\s")[0];

        user.setFirstName(firstName);
        user.setLastName(lastName);

        userService.save(user);
    }

    @Override
    public String getCommandName() {
        return "NEW_USER_ACCEPT_REGISTRATION_NAMES";
    }

    @Override
    public String getSessionClassName() {
        return NewUserSession.class.getSimpleName();
    }
}
