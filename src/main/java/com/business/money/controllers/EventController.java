package com.business.money.controllers;

import com.business.money.DTOs.event.CreateEventDTO;
import com.business.money.DTOs.event.ParticipantEventDTO;
import com.business.money.DTOs.event.EventResponseDTO;
import com.business.money.entities.EventEntity;
import com.business.money.entities.UserEntity;
import com.business.money.exception.exceptions.NotFoundException;
import com.business.money.exception.exceptions.UserAlreadyExistsException;
import com.business.money.mappers.EventMapper;
import com.business.money.services.EventService;
import com.business.money.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;
    private final EventMapper eventMapper;
    private final UserService userService;

    @PostMapping
    public EventResponseDTO saveNewEvent(@RequestBody @Valid CreateEventDTO createEventDTO) throws NotFoundException {
        EventEntity eventEntity = eventMapper.toEventEntity(createEventDTO);
        EventEntity event = eventService.save(eventEntity);
        return eventMapper.toEventResponseDTO(event);
    }

    @PostMapping("/subscribe")
    public EventResponseDTO addParticipant(@RequestBody @Valid ParticipantEventDTO participantEventDTO) throws NotFoundException, UserAlreadyExistsException {
        EventEntity eventForSubscribe = eventService.findById(participantEventDTO.getEventId());
        UserEntity user = userService.findById(participantEventDTO.getUserId());
        Set<EventEntity> eventsForUser = user.getParticipantOf();
        if (eventsForUser.stream().anyMatch(event -> event.getStartTime().equals(eventForSubscribe.getStartTime())))
            throw new NotFoundException("Пользователь уже записан на мероприятие идущее в это же время");
        EventEntity changedEvent = eventService.addParticipant(eventForSubscribe, user);
        return eventMapper.toEventResponseDTO(changedEvent);
    }

    @PutMapping("/unsubscribe")
    public EventResponseDTO removeParticipant(@RequestBody @Valid ParticipantEventDTO participantEventDTO) throws NotFoundException {
        EventEntity event = eventService.findById(participantEventDTO.getEventId());
        UserEntity user = userService.findById(participantEventDTO.getUserId());
        EventEntity newEvent = eventService.removeParticipant(event, user);
        return eventMapper.toEventResponseDTO(newEvent);
    }
}