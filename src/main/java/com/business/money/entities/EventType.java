package com.business.money.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "event_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EventType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private int tribePointsAmount;

    private int coinsAmount;

//    @OneToMany
//    @JoinColumn(name = "type")
//    private List<EventEntity> eventEntities;
}
