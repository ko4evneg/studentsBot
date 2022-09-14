package ru.trainithard.pollerbot.service.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.trainithard.pollerbot.util.MessageConstructor;

import java.util.List;

@Component
public class InputHandlerCommand extends AbstractCommand {
    @Override
    public BotApiMethodMessage execute(Update update) {
        //get session command
        List<MessageConstructor.Button> lineOne = List.of(new MessageConstructor.Button("Начать регистрацию", CommandName.REGISTER));
        return messageConstructor.constructTextButtons(getChatId(update), "Вас приветствует TrainItHard бот. Для использования требуется регистрация: ", List.of(lineOne));
    }

    private Long getChatId(Update update) {
        return update.getMessage().getChatId();
    }

    @Override
    public CommandName getCommandName() {
        return CommandName.INPUT_HANDLER;
    }
}
