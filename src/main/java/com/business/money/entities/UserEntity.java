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
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "clan_points")
    private int clanPoints;

    @Column(name = "coins")
    private int coins;

    @Column(name = "active")
    private boolean active;

    @ToString.Exclude
    @ManyToMany(mappedBy = "authors")
    private Set<EventEntity> authorOf;

    @ManyToMany(mappedBy = "participants")
    private Set<EventEntity> participantOf;

    @ManyToOne
    @JoinColumn(name = "clan")
    private ClanEntity clan;
}
