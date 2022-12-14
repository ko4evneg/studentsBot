package ru.trainithard.pollerbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.trainithard.pollerbot.model.Lesson;

import java.util.List;
import java.util.Set;

public interface LessonsRepository extends JpaRepository<Lesson, Integer> {
    @Query(value = "SELECT l FROM Lesson l WHERE :keyword member of l.keywords")
    List<Lesson> findByKeyword(@Param("keyword") String keyword);

    @Query(value = "SELECT keyword FROM Keywords", nativeQuery = true)
    Set<String> findAllKeywords();
}
