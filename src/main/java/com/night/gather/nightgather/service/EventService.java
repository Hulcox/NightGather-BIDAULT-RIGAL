package com.night.gather.nightgather.service;

import com.night.gather.nightgather.dto.EventDto;
import com.night.gather.nightgather.entity.Event;
import com.night.gather.nightgather.mapper.EventMapper;
import com.night.gather.nightgather.repository.EventRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class EventService {

    @Autowired
    private final EventRepository eventRepository;

    private final EventMapper eventMapper;

    public List<EventDto> getAllEvents(Pageable pageable){
        return eventRepository.findAll(pageable).map(eventMapper::toDto).getContent();
    }

    public EventDto getEventById(Long id){
        Optional<Event> eventOptional = eventRepository.findById(id);
        if(eventOptional.isPresent()){
            return eventMapper.toDto(eventOptional.get());
        }
        return null;
    }
}
