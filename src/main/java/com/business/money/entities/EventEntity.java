package com.business.money.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "events")
@Getter
@Setter
@ToString
public class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "events_id_seq")
    @SequenceGenerator(name = "events_id_seq", sequenceName = "events_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
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
            name = "events_participants",
            joinColumns = @JoinColumn(name = "event_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "participant_id", referencedColumnName = "id")
    )
    private Set<UserEntity> participants = new HashSet<>();

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "quote")
    private Integer quote;

    @Column(name = "clan_only")
    private Boolean clanOnly;

//    @PostLoad
//    public void loadEventHandler() {
//        if (participants != null) participants = new HashSet<>();
//    }
}
