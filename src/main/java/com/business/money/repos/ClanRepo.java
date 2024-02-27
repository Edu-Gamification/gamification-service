package com.business.money.repos;

import com.business.money.DTOs.user.UserResponseDTO;
import com.business.money.entities.ClanEntity;
import com.business.money.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ClanRepo extends JpaRepository<ClanEntity, Long> {
    Optional<ClanEntity> findByName(String name);
}
