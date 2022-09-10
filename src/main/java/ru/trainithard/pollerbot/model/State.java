package ru.trainithard.pollerbot.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum State {
    NEW_USER_FIRST_REQUEST("Вас приветствует TrainItHard бот! Для использования нужна авторизация. Хотите пройти? (да/нет)"),
    REGISTRATION_NAMES_STEP("Для регистрации введите Фамилию и Имя (в таком же порядке):"),
    REGISTRATION_EMAIL_STEP("Введите email для связи и оповещений:"),
    ;

    private final String text;
}
