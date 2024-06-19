package com.night.gather.nightgather.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "rate")
public class Rate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rate")
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id_user")
    private User user;

    @Column(name = "rate", nullable = false)
    private int rate;

    @Column(name= "comment", length = 200)
    private String comment;
}
