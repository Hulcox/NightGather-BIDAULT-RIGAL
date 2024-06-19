package com.night.gather.nightgather.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "events")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_event")
    private Long id;

    @Column(name="address", nullable = false, length = 150)
    private String address;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "participants_events",
            joinColumns = @JoinColumn(name = "event_id", referencedColumnName = "id_event"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id_user")
    )
    private List<User> participants;

    @Column(name = "number_of_places", nullable = false)
    private Integer numberOfPlaces;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "datetime", nullable = false)
    private LocalDateTime datetime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id", referencedColumnName = "id_type")
    private Type type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizer_id", referencedColumnName = "id_user")
    private User organizer;
}
