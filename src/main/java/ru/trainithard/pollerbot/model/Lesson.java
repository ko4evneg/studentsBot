package ru.trainithard.pollerbot.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "lessons")
public class Lesson {
    @Id
    @Column(name = "number")
    private Integer number;

    @Column(name = "title")
    private String title;

    @Column(name = "url")
    private String url;

    @ElementCollection
    @CollectionTable(name = "keywords", joinColumns = {@JoinColumn(name = "lesson_number")})
    @Column(name="keyword")
    private List<String> keywords;
}
