package ru.trainithard.pollerbot.service.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.trainithard.pollerbot.model.RegularUserSession;
import ru.trainithard.pollerbot.service.dto.UpdateUserSession;

import java.util.List;

import static ru.trainithard.pollerbot.util.MessageConstructor.Button;

@Component
public class RegularUserGetLessonsCommand extends AbstractCommand {
    @Override
    public SendMessage execute(UpdateUserSession updateUserSession) {
        List<Button> lineOne = List.of(new Button("В меню", CommandName.USER_GET_MENU));

        return messageConstructor.constructTextButtons(updateUserSession.getChatId(), "Уроки:", List.of(lineOne));
    }

    @Override
    public String getCommandName() {
        return "USER_GET_LESSONS";
    }

    @Override
    public String getSessionClassName() {
        return RegularUserSession.class.getSimpleName();
    }
}
