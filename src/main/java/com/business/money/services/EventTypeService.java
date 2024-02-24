package com.business.money.services;

import com.business.money.entities.EventType;
import com.business.money.repos.EventTypeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventTypeService {
    private final EventTypeRepo eventTypeRepo;

    public Optional<EventType> findEventTypeByName(String name) {
        return eventTypeRepo.findEventTypeByName(name);
    }
}
