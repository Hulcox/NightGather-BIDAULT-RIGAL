package com.night.gather.nightgather.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;

    @Column(name = "email", nullable = false, length = 50)
    private String email;
    @Column(name = "password",nullable = false, length = 20)
    private String password;

    @Column(name = "lastname",nullable = false, length = 50)
    private String lastName;
    @Column(name = "firstname",nullable = false, length = 50)
    private String firstName;
    @Column(name = "username",nullable = false, length = 50)
    private String userName;
    @Column(name = "age",nullable = false)
    private int age;

    @Column(name = "bio")
    private String bio;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Rate> rates;

    @Column(name = "profile_picture")
    private String profilePicture;

    @Column(name = "region",nullable = false, length = 100)
    private String region;
    @Column(name = "city",nullable = false, length = 100)
    private String city;
    @Column(name = "address",nullable = false, length = 100)
    private String address;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "participants_events",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<Event> events;

    @OneToMany(mappedBy = "organizer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Event> organizedEvents;
}
