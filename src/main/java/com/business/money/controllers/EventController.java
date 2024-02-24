package com.business.money.controllers;

import com.business.money.DTOs.event.CreateEventDTO;
import com.business.money.DTOs.event.EventResponseDTO;
import com.business.money.entities.EventEntity;
import com.business.money.exception.exceptions.EventTypeNotFoundException;
import com.business.money.mappers.EventMapper;
import com.business.money.services.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;
    private final EventMapper eventMapper;

    @GetMapping("/")
    public List<EventEntity> getAllEvents() {
        return eventService.getAllEvents();
    }

    @PostMapping("/")
    public EventResponseDTO saveNewEvent(@RequestBody @Valid CreateEventDTO createEventDTO) throws EventTypeNotFoundException {
        EventEntity eventEntity = eventMapper.toEventEntity(createEventDTO);
        EventResponseDTO eventResponseDTO = eventMapper.toEventResponceDTO(eventService.save(eventEntity));
//        System.out.println();
        return eventResponseDTO;
    }
}
