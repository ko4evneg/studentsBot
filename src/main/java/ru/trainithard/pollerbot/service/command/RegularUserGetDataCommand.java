package ru.trainithard.pollerbot.service.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.trainithard.pollerbot.model.NewUserSession;
import ru.trainithard.pollerbot.model.RegularUserSession;
import ru.trainithard.pollerbot.model.User;
import ru.trainithard.pollerbot.service.dto.UpdateUserSession;

import java.util.List;

import static ru.trainithard.pollerbot.util.MessageConstructor.Button;

@Component
public class RegularUserGetDataCommand extends AbstractCommand {
    @Override
    public SendMessage execute(UpdateUserSession updateUserSession) {
        List<Button> lineOne = List.of(new Button("В меню", CommandName.USER_GET_MENU));

        return messageConstructor.constructTextButtons(updateUserSession.getChatId(), getUserData(updateUserSession), List.of(lineOne));
    }

    private String getUserData(UpdateUserSession updateUserSession) {
        User user = updateUserSession.getUser();
        return String.format("Ваши данные: \r\n ФИО: %s %s\r\n email: %s \r\n id: %d",
                user.getFirstName(), user.getLastName(), user.getEmail(), user.getId());
    }

    @Override
    public String getCommandName() {
        return "USER_GET_DATA";
    }

    @Override
    public String getSessionClassName() {
        return RegularUserSession.class.getSimpleName();
    }
}
