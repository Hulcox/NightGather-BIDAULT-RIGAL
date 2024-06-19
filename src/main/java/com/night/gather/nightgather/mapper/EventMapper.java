package com.night.gather.nightgather.mapper;

import com.night.gather.nightgather.dto.EventDto;
import com.night.gather.nightgather.dto.UserDto;
import com.night.gather.nightgather.entity.Event;
import com.night.gather.nightgather.entity.User;

import java.util.List;

public interface EventMapper {
    EventDto toDto(Event event);
    Event toEntity(EventDto eventDto);
    List<EventDto> toDtos(List<Event> events);
    List<Event> toEntities(List<EventDto> eventsDto);
}
