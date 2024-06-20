package com.night.gather.nightgather.mapper;

import com.night.gather.nightgather.dto.EventDto;
import com.night.gather.nightgather.entity.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = {TypeMapper.class, UserMapper.class})
public interface EventMapper {

    EventDto toDto(Event event);

    @Named("toDtoInPage")
    @Mapping(target = "participants", ignore = true)
    EventDto toDtoInPage(Event event);

    Event toEntity(EventDto eventDto);

    List<EventDto> toDtos(List<Event> events);

    List<Event> toEntities(List<EventDto> eventsDto);
}
