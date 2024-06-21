package com.night.gather.nightgather.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.night.gather.nightgather.dto.EventDto;
import com.night.gather.nightgather.entity.Type;
import com.night.gather.nightgather.entity.TypeEvent;
import com.night.gather.nightgather.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.night.gather.nightgather.security.AuthenticationWithJwt.verifyJwt;

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

    @GetMapping("/date")
    public ResponseEntity<List<EventDto>> getAllEventsByDate(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<EventDto> events = eventService.getAllEventsOrderByDate(pageable);
        return ResponseEntity.ok(events);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<EventDto>> getEventsByType(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @PathVariable TypeEvent type) {
        Pageable pageable = PageRequest.of(page, size);
        List<EventDto> events = eventService.getEventsByType(pageable, type);
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

    @PostMapping
    public ResponseEntity<EventDto> createEvent(@RequestBody EventDto eventDto, @RequestHeader("Authorization") final Optional<String> token) {
        if (token.isPresent() && verifyJwt(token.get()) != null) {
            return ResponseEntity.ok(eventService.createEvent(eventDto));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventDto> updateEvent(@PathVariable Long id, @RequestBody EventDto eventDto, @RequestHeader("Authorization") final Optional<String> token) {
        if (token.isPresent() && verifyAuthor(token.get(), id)) {
                return ResponseEntity.ok(eventService.updateEvent(id, eventDto));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EventDto> deleteEvent(@PathVariable Long id, @RequestHeader("Authorization") final Optional<String> token) {
//        if (token.isPresent() && verifyAuthor(token.get(), id)) {
            eventService.deleteEvent(id);
            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    private boolean verifyAuthor(String token, Long idEvent) {
        DecodedJWT decodedJWT = verifyJwt(token);
        String username = decodedJWT.getClaim("username").asString();
        EventDto eventDto = eventService.getEventById(idEvent);
        if (eventDto != null) {
            return eventDto.getOrganizer().getUsername().equals(username);
        }
        return false;
    }
}
