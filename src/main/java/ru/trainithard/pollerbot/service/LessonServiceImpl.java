package ru.trainithard.pollerbot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.trainithard.pollerbot.model.Lesson;
import ru.trainithard.pollerbot.repository.LessonsRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    @Override
    public Optional<Lesson> findByNumber(int number) {
        return repository.findById(number);
    }

    @Override
    public List<Lesson> findByKeyword(String keyword) {
        List<Lesson> lessons = repository.findByKeyword(keyword.toUpperCase());
        lessons.addAll(repository.findByKeyword(keyword.toLowerCase()));
        lessons.addAll(repository.findByKeyword(keyword));
        return lessons;
    }

    @Override
    public Set<String> findAllKeywords() {
        return repository.findAllKeywords();
    }
}
