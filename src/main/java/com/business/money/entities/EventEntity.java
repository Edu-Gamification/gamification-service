package com.business.money.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "events")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    private String description;

    @ManyToOne
    @JoinColumn(name = "type")
    private EventType type;

    @ToString.Exclude
    @ManyToMany
    @JoinTable(
            name = "events_authors",
            joinColumns = @JoinColumn(name = "event_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id")
    )
    private Set<UserEntity> authors;

    @ManyToMany
    @JoinTable(
            name = "events_members",
            joinColumns = @JoinColumn(name = "event_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "participant_id", referencedColumnName = "id")
    )
    private Set<UserEntity> participants;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private int quote;

    private boolean clanOnly;
}
