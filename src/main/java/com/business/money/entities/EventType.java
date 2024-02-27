package com.business.money.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "event_types")
@Getter
@Setter
@ToString
public class EventType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "tribe_points_amount")
    private int tribePointsAmount;

    @Column(name = "coins_amount")
    private int coinsAmount;

//    @OneToMany
//    @JoinColumn(name = "type")
//    private List<EventEntity> eventEntities;
}
