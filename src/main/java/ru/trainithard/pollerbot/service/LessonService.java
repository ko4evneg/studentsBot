package ru.trainithard.pollerbot.service;

import ru.trainithard.pollerbot.model.Lesson;

import java.util.List;

public interface LessonService {
    List<Lesson> findLatest(int count);
}
