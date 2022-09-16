package ru.trainithard.pollerbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.trainithard.pollerbot.model.User;

import java.util.List;

public interface DataJpaUserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT new java.lang.Long(u.chatId) FROM User u")
    List<Long> findAllChatIds();
}
