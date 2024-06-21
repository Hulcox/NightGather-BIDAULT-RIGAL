package com.night.gather.nightgather.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "rates")
@Data
public class Rate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rate")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id_user")
    private User user;

    @Column(name = "rating", nullable = false)
    private int rating;

    @Column(name= "comment", length = 200)
    private String comment;
}
