package ru.trainithard.pollerbot.service.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.trainithard.pollerbot.model.NewUserSession;
import ru.trainithard.pollerbot.service.dto.UpdateUserSession;

@Component
public class NewUserStartRegistrationCommand extends AbstractCommand {
    {
        stepNumber = 2;
    }

    @Override
    public SendMessage execute(UpdateUserSession updateUserSession) {
        updateUserSession.getSession().stepForward();
        saveSession(updateUserSession);

        return messageConstructor.construct(updateUserSession.getChatId(), "Введите ваши данные в формате: Имя Фамилия");
    }

    @Override
    public String getCommandName() {
        return "NEW_USER_START_REGISTRATION";
    }

    @Override
    public String getSessionClassName() {
        return NewUserSession.class.getSimpleName();
    }
}
