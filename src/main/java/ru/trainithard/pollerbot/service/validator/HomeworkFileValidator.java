package ru.trainithard.pollerbot.service.validator;

import org.springframework.stereotype.Component;
import ru.trainithard.pollerbot.service.dto.UserMessage;

@Component
public class HomeworkFileValidator {
    private final static long MAX_SIZE = 10 * 1024 * 1024; 
    
    public boolean validate(UserMessage userMessage){
        if (!userMessage.hasFile()) {
            return false;
        }
        String fileName = userMessage.getFile().getFileName();
        return isArchive(fileName) && hasSizeBelowThreshold(userMessage);
    }

    private boolean hasSizeBelowThreshold(UserMessage userMessage) {
        return userMessage.getFile().getFileSize() < MAX_SIZE;
    }

    private boolean isArchive(String fileName) {
        return fileName.endsWith(".zip") || fileName.endsWith(".rar");
    }
}
