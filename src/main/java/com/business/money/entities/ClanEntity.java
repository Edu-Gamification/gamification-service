package com.business.money.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "clans")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clan_id_seq")
    @SequenceGenerator(name = "clan_id_seq", sequenceName = "clan_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "points_amount")
    private Integer poIntegersAmount;

    @OneToMany(mappedBy = "clan")
    private Set<UserEntity> members;
}
