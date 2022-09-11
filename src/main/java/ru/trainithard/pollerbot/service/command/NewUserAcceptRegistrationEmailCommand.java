package ru.trainithard.pollerbot.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
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

    @Override
    public SendMessage execute(UpdateUserSession updateUserSession) {
        if (validator.validate(getMessage(updateUserSession))) {
            saveUserEmailRole(updateUserSession, Role.USER);

            updateUserSession.setSession(new RegularUserSession());
            saveSession(updateUserSession);

            List<MessageConstructor.Button> lineOne = List.of(new MessageConstructor.Button("В меню", CommandName.USER_GET_MENU));
            return messageConstructor.constructTextButtons(getChatId(updateUserSession), "Успешная регистрация!", List.of(lineOne));
        }
        return messageConstructor.construct(getChatId(updateUserSession), "Введен некорректный email, попробуйте ввести еще раз:");
    }

    private void saveUserEmailRole(UpdateUserSession updateUserSession, Role role) {
        User user = updateUserSession.getUser();
        user.setEmail(getMessage(updateUserSession));
        user.setRole(role);
        userService.save(updateUserSession.getUser());
    }

    @Override
    public CommandName getCommandName() {
        return CommandName.NEW_USER_ACCEPT_REGISTRATION_EMAILS;
    }
}
