package ru.trainithard.pollerbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.trainithard.pollerbot.model.Lesson;

public interface LessonsRepository extends JpaRepository<Lesson, Integer> {
}
