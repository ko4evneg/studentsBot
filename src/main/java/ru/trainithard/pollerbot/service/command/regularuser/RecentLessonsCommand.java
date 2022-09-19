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

import static ru.trainithard.pollerbot.service.command.CommandName.RECENT_LESSONS;

@Component
@RequiredArgsConstructor
public class RecentLessonsCommand extends AbstractCommand {
    private static final int LESSONS_QUANTITY_TO_FETCH = 3;

    private final LessonService lessonService;
    @Override
    public BotApiMethodMessage execute(UserMessage userMessage) {
        return getCustomMessage(userMessage, composeLessonsMessage());
    }

    private String composeLessonsMessage() {
        List<Lesson> lessons = lessonService.findLatest(LESSONS_QUANTITY_TO_FETCH);
        return "Список последних уроков: \r\n" + getAllLessonsString(lessons);
    }

    private String getAllLessonsString(List<Lesson> lessons) {
        return lessons.stream()
                .sorted(Comparator.comparing(Lesson::getNumber))
                .map(this::getLessonString)
                .reduce(String::concat)
                .orElse("уроков нет");
    }

    private String getLessonString(Lesson lesson) {
        return String.format("%d: %s \r\n %s\r\n", lesson.getNumber(), lesson.getTitle(), lesson.getUrl());
    }

    @Override
    public CommandName getCommandName() {
        return RECENT_LESSONS;
    }
}
