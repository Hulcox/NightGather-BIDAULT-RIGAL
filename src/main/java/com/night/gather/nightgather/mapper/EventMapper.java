package com.night.gather.nightgather.mapper;

import com.night.gather.nightgather.dto.EventDto;
import com.night.gather.nightgather.entity.Event;
import org.mapstruct.Mapper;
import org.springframework.data.convert.TypeMapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class, TypeMapper.class})
public interface EventMapper {
    EventDto toDto(Event event);
    Event toEntity(EventDto eventDto);
    List<EventDto> toDtos(List<Event> events);
    List<Event> toEntities(List<EventDto> eventsDto);
}
