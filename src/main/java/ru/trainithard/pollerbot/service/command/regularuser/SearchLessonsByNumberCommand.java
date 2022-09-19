package ru.trainithard.pollerbot.service.command.regularuser;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import ru.trainithard.pollerbot.model.Lesson;
import ru.trainithard.pollerbot.service.LessonService;
import ru.trainithard.pollerbot.service.command.AbstractCommand;
import ru.trainithard.pollerbot.service.command.CommandName;
import ru.trainithard.pollerbot.service.dto.UserMessage;

import java.util.Optional;

import static ru.trainithard.pollerbot.service.command.CommandName.LESSONS_MENU;
import static ru.trainithard.pollerbot.service.command.CommandName.SEARCH_LESSON_BY_NUMBER;

@Component
@RequiredArgsConstructor
public class SearchLessonsByNumberCommand extends AbstractCommand {
    private final LessonService lessonService;

    @Override
    public BotApiMethodMessage execute(UserMessage userMessage) {
        if (isFirstInvocation(userMessage)) {
            saveSessionPreviousCommand(userMessage);
            return getStandardTextMessage(userMessage);
        }

        if (!validateInputIsNumber(userMessage)) {
            return getErrorMessage(userMessage);
        }

        Optional<Lesson> lessonOptional = lessonService.findByNumber(getLessonNumber(userMessage));
        userMessage.setPreviousCommandName(LESSONS_MENU);
        saveSession(userMessage);
        if (lessonOptional.isPresent()) {
            return getCustomMessage(userMessage, getLessonString(lessonOptional.get()));
        } else {
            return getCustomMessage(userMessage, "Урок с таким номером не найден!");
        }
    }

    private boolean validateInputIsNumber(UserMessage userMessage) {
        try {
            getLessonNumber(userMessage);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private Integer getLessonNumber(UserMessage userMessage) {
        return Integer.parseInt(userMessage.getMessage());
    }

    private String getLessonString(Lesson lesson) {
        return String.format("Урок %d: %s \r\n %s\r\n", lesson.getNumber(), lesson.getTitle(), lesson.getUrl());
    }

    @Override
    public CommandName getCommandName() {
        return SEARCH_LESSON_BY_NUMBER;
    }
}
