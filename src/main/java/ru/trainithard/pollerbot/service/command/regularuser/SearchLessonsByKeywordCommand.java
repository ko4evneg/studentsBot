package ru.trainithard.pollerbot.service.command.regularuser;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import ru.trainithard.pollerbot.model.Lesson;
import ru.trainithard.pollerbot.service.LessonService;
import ru.trainithard.pollerbot.service.command.AbstractCommand;
import ru.trainithard.pollerbot.service.command.CommandName;
import ru.trainithard.pollerbot.service.dto.UserMessage;

import java.util.Comparator;
import java.util.List;

import static ru.trainithard.pollerbot.service.command.CommandName.LESSONS_MENU;
import static ru.trainithard.pollerbot.service.command.CommandName.SEARCH_LESSON_BY_KEYWORD;

@Component
@RequiredArgsConstructor
public class SearchLessonsByKeywordCommand extends AbstractCommand {
    private final LessonService lessonService;

    @Override
    public BotApiMethodMessage execute(UserMessage userMessage) {
        if (isFirstInvocation(userMessage)) {
            saveSessionPreviousCommand(userMessage);
            return getStandardTextMessage(userMessage);
        }

        if (!validateInputIsSingleWord(userMessage)) {
            return getErrorMessage(userMessage);
        }

        List<Lesson> lessons = lessonService.findByKeyword(userMessage.getMessage());
        userMessage.setPreviousCommandName(LESSONS_MENU);
        saveSession(userMessage);
        if (lessons.isEmpty()) {
            return getCustomMessage(userMessage, "Урок с таким ключевым словом не найден!");
        } else {
            return getCustomMessage(userMessage, getAllLessonsString(lessons));
        }
    }

    private boolean validateInputIsSingleWord(UserMessage userMessage) {
        return userMessage.getMessage().matches("^\\w+$");
    }

    private String getAllLessonsString(List<Lesson> lessons) {
        return lessons.stream()
                .sorted(Comparator.comparing(Lesson::getNumber))
                .map(this::getLessonString)
                .distinct()
                .reduce(String::concat)
                .orElse("уроков нет");
    }

    private String getLessonString(Lesson lesson) {
        return String.format("%d: %s \r\n %s\r\n", lesson.getNumber(), lesson.getTitle(), lesson.getUrl());
    }

    @Override
    public CommandName getCommandName() {
        return SEARCH_LESSON_BY_KEYWORD;
    }
}
