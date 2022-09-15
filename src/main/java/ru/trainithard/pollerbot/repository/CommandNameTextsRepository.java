package ru.trainithard.pollerbot.repository;

import org.springframework.stereotype.Component;
import ru.trainithard.pollerbot.service.command.CommandName;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CommandNameTextsRepository {
    private final Map<CommandName, String[]> map = new HashMap<>();

    @PostConstruct
    public void fill() {
        map.put(CommandName.NO_COMMAND, List.of("Вас приветствует TrainItHard бот. Для использования требуется регистрация: ",
                "Хотите начать регистрацию?").toArray(new String[2]));
        map.put(CommandName.REGISTER_NAMES, List.of("Для регистрации укажите Имя и Фамилию (в таком же порядке): ",
                "Неверные Имя Фамилия, попробуйте еще раз:").toArray(new String[2]));
        map.put(CommandName.REGISTER_EMAIL, List.of("Укажите email для связи: ",
                "Неверный email, попробуйте еще раз:").toArray(new String[2]));
        map.put(CommandName.FINISH_REGISTRATION, List.of("Успешная регистрация!", "").toArray(new String[2]));
    }

    public String getText(CommandName commandName) {
        return map.get(commandName)[0];
    }

    public String getErrorText(CommandName commandName) {
        return map.get(commandName)[1];
    }
}
