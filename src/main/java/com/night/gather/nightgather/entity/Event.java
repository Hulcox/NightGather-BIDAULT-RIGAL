package com.night.gather.nightgather.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "events", indexes = {
        @Index(name = "idx_event_id_type", columnList = "type_id"),
        @Index(name = "idx_event_participants", columnList = "participants_events")
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_event")
    private Long id;

    @Column(name="address", nullable = false, length = 150)
    private String address;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(
            name = "participants_events",
            joinColumns = @JoinColumn(name = "event_id", referencedColumnName = "id_event"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id_user")
    )
    @BatchSize(size = 10)
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
