package ru.trainithard.pollerbot.repository;

import org.springframework.stereotype.Component;
import ru.trainithard.pollerbot.service.command.CommandName;
import ru.trainithard.pollerbot.util.MessageConstructor;

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
        putButtonedReply(NO_COMMAND, getArrayOf("Вас приветствует TrainItHard бот. Для использования требуется регистрация: ",
                "Хотите начать регистрацию?"), List.of(List.of(new Button("Начать регистрацию", REGISTER_NAMES))));

        replies.put(REGISTER_NAMES, getArrayOf("Для регистрации укажите Имя и Фамилию (в таком же порядке): ",
                "Неверные Имя Фамилия, попробуйте еще раз:"));

        replies.put(REGISTER_EMAIL, getArrayOf("Укажите email для связи: ", "Неверный email, попробуйте еще раз:"));

        putButtonedReply(FINISH_REGISTRATION, getArrayOf("Успешная регистрация!", ""),
                List.of(List.of(new Button("В меню", USER_GET_MENU))));

        putButtonedReply(USER_GET_MENU, getArrayOf("МЕНЮ", ""),
                List.of(List.of(new Button("Мои данные", USER_GET_DATA), new Button("Уроки", USER_GET_LESSONS))));
    }

    private void putButtonedReply(CommandName commandName, String[] replyTexts, List<List<Button>> keyboard) {
        replies.put(commandName, replyTexts);
        buttons.put(commandName, keyboard);
    }

    private String[] getArrayOf(String... strings) {
        return strings;
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
