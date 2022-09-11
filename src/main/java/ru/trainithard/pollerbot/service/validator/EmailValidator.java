package ru.trainithard.pollerbot.service.validator;

import org.springframework.stereotype.Component;

@Component
public class EmailValidator {
    public boolean validate(String email) {
        return email.matches("^.+@\\S+\\.\\S+$");
    }
}
