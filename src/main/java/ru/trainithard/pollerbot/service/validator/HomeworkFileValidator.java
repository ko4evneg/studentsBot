package ru.trainithard.pollerbot.service.validator;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Document;
import ru.trainithard.pollerbot.service.dto.UserMessage;

@Component
public class HomeworkFileValidator {
    private final static long MAX_SIZE = 10 * 1024 * 1024; 
    
    public boolean validate(UserMessage userMessage){
        String fileName = getFile(userMessage).getFileName();
        //todo add user check
        return isArchive(fileName) && hasSizeBelowThreshold(userMessage);
    }

    private boolean hasSizeBelowThreshold(UserMessage userMessage) {
        return getFile(userMessage).getFileSize() < MAX_SIZE;
    }

    private boolean isArchive(String fileName) {
        return fileName.endsWith(".zip") || fileName.endsWith(".rar");
    }

    private Document getFile(UserMessage userMessage) {
        return userMessage.getUpdate().getMessage().getDocument();
    }
}
