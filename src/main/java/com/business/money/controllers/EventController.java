package com.business.money.controllers;

import com.business.money.DTOs.event.AddParticipantDTO;
import com.business.money.DTOs.event.CreateEventDTO;
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

    @PostMapping("/assign")
    public EventResponseDTO addParticipant(@RequestBody @Valid AddParticipantDTO addParticipantDTO) throws NotFoundException, UserAlreadyExistsException {
        EventEntity event = eventService.findById(addParticipantDTO.getEventId());
        UserEntity user = userService.findById(addParticipantDTO.getUserId());
        EventEntity changedEvent = eventService.addParticipant(event, user);
        return eventMapper.toEventResponseDTO(changedEvent);
    }
}
