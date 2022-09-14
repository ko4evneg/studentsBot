package ru.trainithard.pollerbot.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.trainithard.pollerbot.model.NewUserSession;
import ru.trainithard.pollerbot.model.RegularUserSession;
import ru.trainithard.pollerbot.model.Role;
import ru.trainithard.pollerbot.model.User;
import ru.trainithard.pollerbot.service.dto.UpdateUserSession;
import ru.trainithard.pollerbot.service.validator.EmailValidator;
import ru.trainithard.pollerbot.util.MessageConstructor;

import java.util.List;

@Component
@RequiredArgsConstructor
public class NewUserAcceptRegistrationEmailCommand extends AbstractCommand {
    private final EmailValidator validator;

    {
        stepNumber = 4;
    }

    @Override
    public SendMessage execute(UpdateUserSession updateUserSession) {
        if (validator.validate(updateUserSession.getMessage())) {
            saveUserEmailRole(updateUserSession, Role.USER);

            updateUserSession.setSession(new RegularUserSession());
            saveSession(updateUserSession);

            List<MessageConstructor.Button> lineOne = List.of(new MessageConstructor.Button("В меню", CommandName.USER_GET_MENU));
            return messageConstructor.constructTextButtons(updateUserSession.getChatId(), "Успешная регистрация!", List.of(lineOne));
        }
        return messageConstructor.construct(updateUserSession.getChatId(), "Введен некорректный email, попробуйте ввести еще раз:");
    }

    private void saveUserEmailRole(UpdateUserSession updateUserSession, Role role) {
        User user = updateUserSession.getUser();
        user.setEmail(updateUserSession.getMessage());
        user.setRole(role);
        userService.save(updateUserSession.getUser());
    }

    @Override
    public String getCommandName() {
        return "NEW_USER_ACCEPT_REGISTRATION_EMAILS";
    }

    @Override
    public String getSessionClassName() {
        return NewUserSession.class.getSimpleName();
    }
}
