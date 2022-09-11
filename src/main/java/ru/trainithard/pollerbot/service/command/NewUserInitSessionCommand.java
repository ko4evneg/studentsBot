package ru.trainithard.pollerbot.service.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.trainithard.pollerbot.service.dto.UpdateUserSession;

import java.util.List;

import static ru.trainithard.pollerbot.util.MessageConstructor.Button;

@Component
public class NewUserInitSessionCommand extends AbstractCommand {
    @Override
    public SendMessage execute(UpdateUserSession updateUserSession) {
        List<Button> lineOne = List.of(new Button("Начать регистрацию", CommandName.NEW_USER_START_REGISTRATION));

        return messageConstructor.constructTextButtons(getChatId(updateUserSession),
                "Вас приветствует TrainItHard бот. Для использования требуется регистрация:", List.of(lineOne));
    }

    @Override
    public CommandName getCommandName() {
        return CommandName.NEW_USER_INIT_COMMAND;
    }
}