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
    private String password;
    private String lastname;
    private String firstname;
    private String username;
    private int age;
    private String bio;
//    private List<RateDto> rates;
    private String profilePicture;
    private String region;
    private String address;
    private String city;
//    private List<EventDto> events;
//    private List<EventDto> organizedEvents;
}

