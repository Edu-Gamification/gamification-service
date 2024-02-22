package com.business.money.services;

import com.business.money.entities.EventEntity;
import com.business.money.repos.EventRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepo eventRepo;

    public List<EventEntity> getAllEvents() {
        return eventRepo.findAll();
    }
}
