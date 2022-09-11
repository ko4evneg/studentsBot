package ru.trainithard.pollerbot.service.validator;

import org.springframework.stereotype.Component;

@Component
public class UserNamesValidator {
    public boolean validate(String lastFirstName) {
        return !lastFirstName.isBlank() && stringContainsBothNames(lastFirstName);
    }

    private boolean stringContainsBothNames(String lastFirstName) {
        return lastFirstName.split("\\s").length == 2;
    }
}
