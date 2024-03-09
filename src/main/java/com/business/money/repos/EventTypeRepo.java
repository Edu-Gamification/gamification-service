package com.business.money.repos;

import com.business.money.entities.domain.EventType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventTypeRepo extends JpaRepository<EventType, Long> {
    Optional<EventType> findEventTypeByName(String name);
}
