package com.night.gather.nightgather.service;

import com.night.gather.nightgather.dto.EventDto;
import com.night.gather.nightgather.entity.Event;
import com.night.gather.nightgather.entity.Type;
import com.night.gather.nightgather.entity.TypeEvent;
import com.night.gather.nightgather.mapper.EventMapper;
import com.night.gather.nightgather.repository.EventRepository;
import com.night.gather.nightgather.repository.TypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class EventService {

    @Autowired
    private final EventRepository eventRepository;

    private final EventMapper eventMapper;

    @Cacheable(value = "events")
    public List<EventDto> getAllEvents(Pageable pageable){
        return eventRepository.findAll(pageable).map(eventMapper::toDtoInPage).getContent();
    }

    @Cacheable(value = "eventsOrderByDate")
    public List<EventDto> getAllEventsOrderByDate(Pageable pageable){
        return eventRepository.findAllOrderByDate(pageable).map(eventMapper::toDto).getContent();
    }

    @Cacheable(value = "eventsByType")
    public List<EventDto> getEventsByType(Pageable pageable, TypeEvent type ){
        return eventRepository.findByType(pageable, type).map(eventMapper::toDto).getContent();
    }

    @Cacheable(value = "event", key = "#id")
    public EventDto getEventById(Long id){
        Optional<Event> eventOptional = eventRepository.findById(id);
        return eventOptional.map(eventMapper::toDto).orElse(null);
    }

    @Caching(
            evict={
                    @CacheEvict(value = "events", allEntries = true),
                    @CacheEvict(value = "eventsOrderByDate", allEntries = true),
                    @CacheEvict(value = "eventsByType", allEntries = true),
                    @CacheEvict(value = "event", allEntries = true)
            }
    )
    public EventDto createEvent(EventDto eventDto) {
        Event event = eventMapper.toEntity(eventDto);
        return eventMapper.toDto(eventRepository.save(event));
    }

    @Caching(
            evict= {@CacheEvict(value = "events", allEntries = true),
                    @CacheEvict(value = "eventsOrderByDate", allEntries = true),
                    @CacheEvict(value = "eventsByType", allEntries = true)},
            put = {@CachePut(value = "event", key = "#id"),
            }
    )
    public EventDto updateEvent(Long id, EventDto eventDto){
        return eventRepository.findById(id)
                .map(existingEvent -> {
                    Event event = eventMapper.toEntity(eventDto);
                    event.setId(id);
                    if (Objects.nonNull(existingEvent.getOrganizer())) {
                        event.setOrganizer(existingEvent.getOrganizer());
                    }
                    if (Objects.nonNull(existingEvent.getType())) {
                        event.setType(existingEvent.getType());
                    }
                    if(Objects.nonNull(existingEvent.getParticipants()) || !existingEvent.getParticipants().isEmpty()) {
                        event.setParticipants(existingEvent.getParticipants());
                    }
                    return eventMapper.toDto(eventRepository.save(event));
                })
                .orElse(null);
    }

    @Caching(
            evict={
                    @CacheEvict(value = "events", allEntries = true),
                    @CacheEvict(value = "eventsOrderByDate", allEntries = true),
                    @CacheEvict(value = "event", allEntries = true),
                    @CacheEvict(value = "eventsByType", allEntries = true)
            }
    )
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
}
