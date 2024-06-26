package com.night.gather.nightgather.dto;

import com.night.gather.nightgather.entity.TypeEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeDto {

    private Long id;
    private TypeEvent type;
//    private List<EventDto> events;
}