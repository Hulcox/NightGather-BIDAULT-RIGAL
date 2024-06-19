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
    private String name;
    private String firstName;
    private String userName;
    private int age;
    private String bio;
    private List<Long> rates;
    private String profilePicture;
    private String region;
    private String city;
    private List<Long> events;
    private List<Long> organizedEvents;
}

