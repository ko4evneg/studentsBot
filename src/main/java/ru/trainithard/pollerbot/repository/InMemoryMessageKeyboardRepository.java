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
        putTextButton(USER_GET_DATA, getMessage("Ваши данные"), getMarkup(1),
                "В меню", USER_GET_MENU.toString());
        putTextButton(LESSONS_MENU, getMessage("Выберите действие:"), getMarkup(2, 2),
                "Показать последние уроки", RECENT_LESSONS.toString(), "Поиск по номеру", SEARCH_LESSON_BY_NUMBER.toString(),
                "Поиск по ключевому слову", SEARCH_LESSON_BY_KEYWORD.toString(), "Все ключевые слова", SHOW_ALL_KEYWORDS.toString());
        putTextButton(SHOW_ALL_KEYWORDS, getMessage("Доступные ключевые слова:\r\n"), getMarkup(2),
                "В меню", USER_GET_MENU.toString(), "Найти по слову", SEARCH_LESSON_BY_KEYWORD.toString());
        putTextButton(SEARCH_LESSON_BY_NUMBER, getMessage("Введите номер урока:", "Необходимо ввести число, попробуйте еще раз:"),
                getMarkup(2), "В меню", USER_GET_MENU.toString(), "Другой номер", SEARCH_LESSON_BY_NUMBER.toString());
        putTextButton(SEARCH_LESSON_BY_KEYWORD, getMessage("Введите ключевое слово:", "Необходимо ввести ключевое слово, попробуйте еще рaз!"),
                getMarkup(3), "В меню", USER_GET_MENU.toString(), "Другое слово", SEARCH_LESSON_BY_KEYWORD.toString(),
                "К списку слов", SHOW_ALL_KEYWORDS.toString());
        putTextButton(RECENT_LESSONS, getMessage("Список последних уроков:"), getMarkup(1),
                "В меню", USER_GET_MENU.toString());
        putText(USER_UPLOAD_HOMEWORK, "Перенесите файл в чат. Файл должен быть .zip или .rar архивом, менее 10МБ.",
                "Неверный формат файла. Загрузите правильный файл.");
        putTextButton(FINISH_UPLOAD_HOMEWORK, getMessage("Домашнее задание успешно загружено"), getMarkup(1),
                "В меню", USER_GET_MENU.toString());

        //ADMIN USER
        putTextButton(ADMIN_GET_MENU, getMessage("ADMIN MENU:"), getMarkup(2),
                "Notify all", CONSTRUCT_NOTIFICATION.toString(), "Download homework", LIST_HOMEWORKS_FOLDERS.toString());
        putText(CONSTRUCT_NOTIFICATION, "Enter notification text:");
        putText(LIST_HOMEWORKS_FOLDERS, "Select student:");
        putText(DOWNLOAD_HOMEWORK, "Select homework:");
        putText(NOTIFY_ALL, "Notification has been sent!", "Notification has failed!");

        //TEXT COMMANDS
        putText(RESET_SESSION, "Сессия успешно сброшена");
        putText(HELP, "Помощь:\r\n /reset - сброс сессии пользователя");
        putText(ELEVATE, "Elevation successful", "No rights for elevation");
        putText(DELEVATE, "De-elevation successful", "No rights for de-elevation");

    }

    private void putTextButton(CommandName commandName, String[] messages, int[] rowsMarkup, String... buttonsData) {
        repository.put(commandName, new MessageKeyboard(messages, rowsMarkup, buttonsData));
    }

    private void putText(CommandName commandName, String... messages) {
        repository.put(commandName, new MessageKeyboard(messages));
    }

    private String[] getMessage(String... messages) {
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
