package ru.trainithard.pollerbot.service;

import ru.trainithard.pollerbot.model.Lesson;

import java.util.List;
import java.util.Optional;

public interface LessonService {
    List<Lesson> findLatest(int count);

    Optional<Lesson> findByNumber(int number);
}
