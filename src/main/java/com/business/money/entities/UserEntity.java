package com.business.money.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String surname;

    private String patronymic;

    private String email;

    private String password;

    private int clanPoints;

    private int coins;

    private boolean active;

    @ToString.Exclude
    @ManyToMany(mappedBy = "authors")
    private Set<EventEntity> authorOf;

    @ManyToMany(mappedBy = "participants")
    private Set<EventEntity> participantOf;
}
