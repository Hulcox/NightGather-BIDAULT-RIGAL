package com.night.gather.nightgather.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import java.util.List;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "email", nullable = false, length = 50)
    private String email;
    @Column(name = "password",nullable = false, length = 20)
    private String password;

    @Column(name = "name",nullable = false, length = 50)
    private String name;
    @Column(name = "first_name",nullable = false, length = 50)
    private String firstName;
    @Column(name = "user_name",nullable = false, length = 20)
    private String userName;
    @Column(name = "age",nullable = false)
    private int age;

    @Column(name = "bio")
    private String bio;
    @Column(name = "rating",nullable = false)
    private double rating = 0.0;
    @Column(name = "profile_picture")
    private String profilePicture;

    @Column(name = "region",nullable = false, length = 100)
    private String region;
    @Column(name = "city",nullable = false, length = 100)
    private String city;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Event> events;
}
