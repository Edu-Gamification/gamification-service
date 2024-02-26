package com.business.money.services;

import com.business.money.entities.EventType;
import com.business.money.exception.exceptions.NotFoundException;
import com.business.money.repos.EventTypeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventTypeService {
    private final EventTypeRepo eventTypeRepo;
    public EventType findEventTypeByName(String name) throws NotFoundException {
        return eventTypeRepo.findEventTypeByName(name).orElseThrow(() -> new NotFoundException("Тип события не найден"));
    }
}
