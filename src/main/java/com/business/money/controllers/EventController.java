package com.business.money.controllers;

import com.business.money.entities.EventEntity;
import com.business.money.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping("")
    public List<EventEntity> getAllEvents() {
        Date temp = new Date();
        System.out.println(temp);
        return eventService.getAllEvents();
    }
}
