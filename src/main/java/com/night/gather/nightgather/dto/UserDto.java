package com.night.gather.nightgather.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

//    public UserDto(Long id, String email, String password, String lastname, String firstname, String username, int age, String bio, List<RateDto> rates, String profilePicture, String region, String address, String city) {
//        this.id = id;
//        this.email = email;
//        this.password = password;
//        this.lastname = lastname;
//        this.firstname = firstname;
//        this.username = username;
//        this.age = age;
//        this.bio = bio;
//        this.rates = rates;
////        this.globalRating = rates.stream()
////                .mapToDouble(RateDto::getRating)
////                .average()
////                .orElse(0.0);
//        this.profilePicture = profilePicture;
//        this.region = region;
//        this.address = address;
//        this.city = city;
//    }

    private Long id;
    private String email;
    private String password;
    private String lastname;
    private String firstname;
    private String username;
    private int age;
    private String bio;
    private List<RateDto> rates;
    private double globalRating;
    private String profilePicture;
    private String region;
    private String address;
    private String city;
//    private List<EventDto> events;
//    private List<EventDto> organizedEvents;

}

