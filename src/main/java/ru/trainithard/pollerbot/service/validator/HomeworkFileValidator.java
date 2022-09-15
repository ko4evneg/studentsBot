package ru.trainithard.pollerbot.service.validator;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Document;
import ru.trainithard.pollerbot.service.dto.UserMessage;

@Component
public class HomeworkFileValidator {
    public boolean validate(UserMessage userMessage){
        String fileName = getFile(userMessage).getFileName();
        //todo add user check
        return (fileName.endsWith(".zip") || fileName.endsWith(".rar"));
    }

    private Document getFile(UserMessage userMessage) {
        return userMessage.getUpdate().getMessage().getDocument();
    }
}
