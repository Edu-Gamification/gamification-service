package com.business.money.services;

import com.business.money.entities.EventEntity;
import com.business.money.entities.EventType;
import com.business.money.entities.UserEntity;
import com.business.money.exception.exceptions.NotFoundException;
import com.business.money.exception.exceptions.UserAlreadyExistsException;
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

//    public List<EventEntity> getAllEvents() {
//        return eventRepo.findAll();
//    }

    public EventEntity save(EventEntity eventEntity) throws NotFoundException {
        String typeName = eventEntity.getType().getName();
        EventType eventType = eventTypeService.findEventTypeByName(typeName);
        eventEntity.setType(eventType);
        Set<UserEntity> authors = new HashSet<>();
        for (var author: eventEntity.getAuthors()) {
            UserEntity authorOptional = userService.findById(author.getId());
            authors.add(authorOptional);
        }
        eventEntity.setAuthors(authors);
        return eventRepo.save(eventEntity);
    }

    public EventEntity findById(Long id) throws NotFoundException {
        return eventRepo.findById(id).orElseThrow(() -> new NotFoundException("Тип события не найден"));
    }

    public EventEntity addParticipant(EventEntity event, UserEntity user) throws UserAlreadyExistsException {
        boolean userIsAuthor = event.getAuthors().stream().anyMatch(author -> author.getEmail().equals(user.getEmail()));
        if (userIsAuthor) throw new UserAlreadyExistsException("Пользователь который хочет записаться на мероприятие является автором этого мероприятия");
        Set<UserEntity> participants = event.getParticipants();
        participants.add(user);
        eventRepo.save(event);
        return event;
    }
}
