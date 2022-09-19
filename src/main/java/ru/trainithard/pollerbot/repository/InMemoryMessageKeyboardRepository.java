package ru.trainithard.pollerbot.repository;

import org.springframework.stereotype.Component;
import ru.trainithard.pollerbot.service.command.CommandName;
import ru.trainithard.pollerbot.service.dto.MessageKeyboard;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

import static ru.trainithard.pollerbot.service.command.CommandName.*;

@Component
public class InMemoryMessageKeyboardRepository implements MessageKeyboardRepository {
    private Map<CommandName, MessageKeyboard> repository = new HashMap<>();

    @PostConstruct
    public void fill() {
        //NEW USER
        putTextButton(NO_COMMAND, getMessage("Вас приветствует TrainItHard бот. Для использования требуется регистрация: "
                ), getMarkup(1),
                "Начать регистрацию", REGISTER_EMAIL.toString());
        putText(REGISTER_NAMES, "Укажите Имя и Фамилию (в таком же порядке): ", "Неверные Имя Фамилия, попробуйте еще раз:");
        putText(REGISTER_EMAIL, "Укажите email для связи: ", "Неверный email, попробуйте еще раз:");
        putTextButton(FINISH_REGISTRATION, getMessage("Успешная регистрация!"), getMarkup(1),
                "В меню", USER_GET_MENU.toString());

        //REGULAR USER
        putTextButton(USER_GET_MENU, getMessage("МЕНЮ"), getMarkup(2, 1),
                "Мои данные", USER_GET_DATA.toString(), "Уроки", LESSONS_MENU.toString(),
                "Загрузить домашнее задание", USER_UPLOAD_HOMEWORK.toString());

        //ADMIN USER
        putTextButton(ADMIN_GET_MENU, getMessage("ADMIN MENU:"), getMarkup(2),
                "Notify all", CONSTRUCT_NOTIFICATION.toString(), "Download homework", LIST_HOMEWORKS_FOLDERS.toString());
    }

    private void putTextButton(CommandName commandName, String[] messages, int[] rowsMarkup, String... buttonsData) {
        repository.put(commandName, new MessageKeyboard(messages, rowsMarkup, buttonsData));
    }

    private void putText(CommandName commandName, String...messages) {
        repository.put(commandName, new MessageKeyboard(messages));
    }

    private String[] getMessage(String...messages) {
        return messages;
    }

    private int[] getMarkup(int... buttonsInRow) {
        return buttonsInRow;
    }

    @Override
    public MessageKeyboard find(CommandName commandName) {
        return repository.get(commandName);
    }
}
