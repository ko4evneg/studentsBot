package ru.trainithard.pollerbot.service.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.trainithard.pollerbot.service.dto.UserMessage;
import ru.trainithard.pollerbot.util.StringMetaDataManager;

@Component
@RequiredArgsConstructor
public class ButtonVersionValidator {
    private final StringMetaDataManager metaDataManager;

    public boolean validate(UserMessage userMessage) {
        long callbackVersion = Long.parseLong(getVersion(userMessage));
        return userMessage.getSessionVersion() == callbackVersion;
    }

    private String getVersion(UserMessage userMessage) {
        return metaDataManager.getMetaDataValue(userMessage.getCallbackData(), "v");
    }
}
