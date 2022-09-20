package ru.trainithard.pollerbot.service;

import ru.trainithard.pollerbot.model.Lesson;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface LessonService {
    List<Lesson> findLatest(int count);

    Optional<Lesson> findByNumber(int number);

    List<Lesson> findByKeyword(String keyword);

    Set<String> findAllKeywords();
}
