package ru.trainithard.pollerbot.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User extends BaseEntity {
    public User(Role role) {
        this.role = role;
    }

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "nick")
    private String nick;

    @Column(name = "email")
    private String email;

    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    private Role role;
}
