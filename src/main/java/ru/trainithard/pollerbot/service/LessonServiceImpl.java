package ru.trainithard.pollerbot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.trainithard.pollerbot.model.Lesson;
import ru.trainithard.pollerbot.repository.LessonsRepository;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {
    private final LessonsRepository repository;

    @Override
    public List<Lesson> findLatest(int count) {
        return repository.findAll().stream()
                .sorted(Comparator.comparing(Lesson::getNumber).reversed())
                .limit(count)
                .toList();
    }
}
