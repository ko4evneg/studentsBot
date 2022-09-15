package ru.trainithard.pollerbot.repository;

import org.springframework.stereotype.Component;
import ru.trainithard.pollerbot.service.command.CommandName;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.trainithard.pollerbot.service.command.CommandName.*;
import static ru.trainithard.pollerbot.util.MessageConstructor.*;

@Component
public class InMemoryCommandNameReplyRepository implements CommandNameReplyRepository {
    private final Map<CommandName, String[]> replies = new HashMap<>();
    private final Map<CommandName, List<List<Button>>> buttons = new HashMap<>();


    @PostConstruct
    public void fill() {
        replies.put(NO_COMMAND, List.of("Вас приветствует TrainItHard бот. Для использования требуется регистрация: ",
                "Хотите начать регистрацию?").toArray(new String[2]));
        replies.put(REGISTER_NAMES, List.of("Для регистрации укажите Имя и Фамилию (в таком же порядке): ",
                "Неверные Имя Фамилия, попробуйте еще раз:").toArray(new String[2]));
        replies.put(REGISTER_EMAIL, List.of("Укажите email для связи: ",
                "Неверный email, попробуйте еще раз:").toArray(new String[2]));
        replies.put(FINISH_REGISTRATION, List.of("Успешная регистрация!", "").toArray(new String[2]));
        replies.put(USER_GET_MENU, List.of("Меню:", "").toArray(new String[2]));
    }

    @Override
    public String getText(CommandName commandName) {
        return replies.get(commandName)[0];
    }

    @Override
    public String getErrorText(CommandName commandName) {
        return replies.get(commandName)[1];
    }

    @Override
    public List<List<Button>> getButtons(CommandName commandName) {
        return buttons.get(commandName);
    }
}
