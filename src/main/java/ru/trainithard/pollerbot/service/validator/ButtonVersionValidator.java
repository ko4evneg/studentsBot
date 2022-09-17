package ru.trainithard.pollerbot.service.validator;

import org.springframework.stereotype.Component;
import ru.trainithard.pollerbot.service.dto.UserMessage;

@Component
public class ButtonVersionValidator {
    public boolean validate(UserMessage userMessage) {
        long callbackVersion = Long.parseLong(userMessage.getCallbackData().split("___")[1]);
        return userMessage.getSessionVersion() == callbackVersion;
    }
}
