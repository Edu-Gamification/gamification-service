package com.business.money.services;

import com.business.money.entities.EventEntity;
import com.business.money.entities.EventType;
import com.business.money.entities.UserEntity;
import com.business.money.exception.exceptions.EventTypeNotFoundException;
import com.business.money.repos.EventRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepo eventRepo;
    private final EventTypeService eventTypeService;
    private final UserService userService;

    public List<EventEntity> getAllEvents() {
        return eventRepo.findAll();
    }

    public EventEntity save(EventEntity eventEntity) throws EventTypeNotFoundException {
        Optional<EventType> eventType = eventTypeService.findEventTypeByName(eventEntity.getType().getName());
        if (eventType.isEmpty()) {
            throw new EventTypeNotFoundException();
        }
        eventEntity.setType(eventType.get());
        Set<UserEntity> authors = new HashSet<>();
        for (var author: eventEntity.getAuthors()) {
            Optional<UserEntity> authorOptional = userService.getUserById(author.getId());
            authorOptional.ifPresent(authors::add);
        }
        eventEntity.setAuthors(authors);
//        System.out.println(eventEntity);
        return eventRepo.save(eventEntity);
    }
}
