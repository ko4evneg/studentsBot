package ru.trainithard.pollerbot.service.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.trainithard.pollerbot.service.dto.UpdateUserSession;

import java.util.List;

import static ru.trainithard.pollerbot.util.MessageConstructor.Button;

@Component
public class RegularUserGetMenuCommand extends AbstractCommand {
    @Override
    public SendMessage execute(UpdateUserSession updateUserSession) {
        List<Button> buttons = List.of(new Button("Мои данные", CommandName.USER_GET_DATA),
                new Button("Уроки", CommandName.USER_GET_LESSONS));
        return messageConstructor.constructTextButtons(updateUserSession.getChatId(), "Главное меню:", List.of(buttons));
    }

    @Override
    public CommandName getCommandName() {
        return CommandName.USER_GET_MENU;
    }
}
