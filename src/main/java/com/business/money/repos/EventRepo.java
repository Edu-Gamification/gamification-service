package com.business.money.repos;

import com.business.money.entities.domain.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepo extends JpaRepository<EventEntity, Long> {

}
