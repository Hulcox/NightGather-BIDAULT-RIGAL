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
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;

    @Column(name = "email", nullable = false, length = 50)
    private String email;
    @Column(name = "password",nullable = false, length = 20)
    private String password;

    @Column(name = "lastname",nullable = false, length = 50)
    private String lastname;
    @Column(name = "firstname",nullable = false, length = 50)
    private String firstname;
    @Column(name = "username",nullable = false, length = 50)
    private String username;
    @Column(name = "age",nullable = false)
    private int age;

    @Column(name = "bio")
    private String bio;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @BatchSize(size = 10)
    private List<Rate> rates;

    @Column(name = "profile_picture")
    private String profilePicture;

    @Column(name = "region",nullable = false, length = 100)
    private String region;
    @Column(name = "city",nullable = false, length = 100)
    private String city;
    @Column(name = "address",nullable = false, length = 100)
    private String address;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "participants_events",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "event_id", referencedColumnName = "id_event")
    )
    @BatchSize(size = 10)
    private List<Event> events;

    @OneToMany(mappedBy = "organizer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @BatchSize(size = 10)
    private List<Event> organizedEvents;
}
