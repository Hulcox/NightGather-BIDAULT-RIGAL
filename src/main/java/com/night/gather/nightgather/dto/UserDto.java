package com.night.gather.nightgather.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String email;
    private String name;
    private String firstName;
    private int age;

    private String bio;
    private double rating;
    private String profilePicture;

    private String region;
    private String city;

    List<EventDto> events;
}
