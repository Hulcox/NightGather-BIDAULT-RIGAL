package com.night.gather.nightgather.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {
    private Long id;
    private String address;
//    private List<UserDto> participants;
    private Integer numberOfPlaces;
    private double price;
    private LocalDateTime datetime;
    private TypeDto type;
    private UserDto organizer;
}
