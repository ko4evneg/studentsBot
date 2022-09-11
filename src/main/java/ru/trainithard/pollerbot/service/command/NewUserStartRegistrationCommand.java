package ru.trainithard.pollerbot.service.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.trainithard.pollerbot.service.dto.UpdateUserSession;

@Component
public class NewUserStartRegistrationCommand extends AbstractCommand {
    @Override
    public SendMessage execute(UpdateUserSession updateUserSession) {
        updateUserSession.getSession().stepForward();
        saveSession(updateUserSession);

        return messageConstructor.construct(getChatId(updateUserSession), "Введите ваши данные в формате: Имя Фамилия");
    }

    @Override
    public CommandName getCommandName() {
        return CommandName.NEW_USER_START_REGISTRATION;
    }
}
