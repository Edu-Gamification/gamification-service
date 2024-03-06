package com.business.money.repos;

import com.business.money.entities.ClanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClanRepo extends JpaRepository<ClanEntity, Long> {
    Optional<ClanEntity> findByName(String name);
}
