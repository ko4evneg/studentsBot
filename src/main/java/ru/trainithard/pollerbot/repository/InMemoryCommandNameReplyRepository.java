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
        //regular user texts
        putButtonedReply(USER_GET_DATA, getArrayOf("Ваши данные", ""),
                List.of(List.of(new Button("В меню", USER_GET_MENU))));
        putButtonedReply(LESSONS_MENU, getArrayOf("Выберите действие:", ""),
                List.of(List.of(new Button("Показать последние уроки", RECENT_LESSONS),
                        new Button("Поиск по номеру", SEARCH_LESSON_BY_NUMBER)),
                        List.of(new Button("Поиск по ключевому слову", SEARCH_LESSON_BY_KEYWORD),
                        new Button("Все ключевые слова", SHOW_ALL_KEYWORDS))));
        putButtonedReply(SHOW_ALL_KEYWORDS, getArrayOf("Доступные ключевые слова:\r\n"),
                List.of(List.of(new Button("В меню", USER_GET_MENU), new Button("Найти по слову", SEARCH_LESSON_BY_KEYWORD))));
        putButtonedReply(SEARCH_LESSON_BY_NUMBER, getArrayOf("Введите номер урока:", "Необходимо ввести число, попробуйте еще раз:"),
                List.of(List.of(new Button("В меню", USER_GET_MENU), new Button("Другой номер", SEARCH_LESSON_BY_NUMBER))));
        putButtonedReply(SEARCH_LESSON_BY_KEYWORD, getArrayOf("Введите ключевое слово:", "Необходимо ввести ключевое слово, попробуйте еще рaз!"),
                List.of(List.of(new Button("В меню", USER_GET_MENU), new Button("Другое слово", SEARCH_LESSON_BY_KEYWORD),
                        new Button("К списку слов", SHOW_ALL_KEYWORDS))));
        putButtonedReply(RECENT_LESSONS, getArrayOf("Список последних уроков:", ""),
                List.of(List.of(new Button("В меню", USER_GET_MENU))));
        putTextReply(USER_UPLOAD_HOMEWORK, getArrayOf("Перенесите файл в чат. Файл должен быть .zip или .rar архивом, менее 10МБ.",
                "Неверный формат файла. Загрузите правильный файл."));
        putButtonedReply(FINISH_UPLOAD_HOMEWORK, getArrayOf("Домашнее задание успешно загружено", ""),
                List.of(List.of(new Button("В меню", USER_GET_MENU))));
    }

    private void putTextReply(CommandName notifyAll, String... replyTexts) {
        replies.put(notifyAll, replyTexts);
    }

    private void putButtonedReply(CommandName commandName, String[] replyTexts, List<List<Button>> keyboard) {
        replies.put(commandName, replyTexts);
        buttons.put(commandName, keyboard);
    }

    private String[] getArrayOf(String... strings) {
        return strings;
    }

    @Override
    public String findText(CommandName commandName) {
        return replies.get(commandName)[0];
    }

    @Override
    public String findErrorText(CommandName commandName) {
        return replies.get(commandName)[1];
    }

    @Override
    public List<List<Button>> findButtons(CommandName commandName) {
        return buttons.get(commandName);
    }
}
