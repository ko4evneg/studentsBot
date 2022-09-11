package ru.trainithard.pollerbot.service.validator;

import org.springframework.stereotype.Component;
import ru.trainithard.pollerbot.exception.NamesValidationException;

@Component
public class UserNamesValidator {
    public void validate(String lastFirstName) {
        if (lastFirstName.isBlank() || !stringContainsBothNames(lastFirstName)) {
            throw new NamesValidationException("Names can't pass validation.");
        }
    }

    private boolean stringContainsBothNames(String lastFirstName) {
        return lastFirstName.split("\\s").length == 2;
    }
}
