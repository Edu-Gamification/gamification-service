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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_types_id_seq")
    @SequenceGenerator(name = "event_types_id_seq", sequenceName = "event_types_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "tribe_points_amount")
    private Integer tribePoIntegersAmount;

    @Column(name = "coins_amount")
    private Integer coinsAmount;

    @OneToMany
    @JoinColumn(name = "type")
    private List<EventEntity> eventEntities;
}
