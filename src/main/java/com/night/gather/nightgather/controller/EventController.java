package com.night.gather.nightgather.controller;

import com.night.gather.nightgather.dto.EventDto;
import com.night.gather.nightgather.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping
    public ResponseEntity<List<EventDto>> getAllEvents(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<EventDto> events = eventService.getAllEvents(pageable);
        return ResponseEntity.ok(events);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDto> getEvent(@PathVariable Long id) {
        EventDto eventDto = eventService.getEventById(id);
        if (eventDto != null) {
            return ResponseEntity.ok(eventDto);
        }
        return ResponseEntity.notFound().build();
    }
}
