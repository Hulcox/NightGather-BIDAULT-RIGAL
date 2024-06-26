package com.night.gather.nightgather.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import java.util.List;

@Entity
@Table(name = "types", indexes = {@Index(name = "idx_type_name", columnList = "type")})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Type extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type")
    private Long id;

    @Column(name = "type", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private TypeEvent type;

    @OneToMany(mappedBy = "type", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @BatchSize(size = 10)
    private List<Event> events;
}

