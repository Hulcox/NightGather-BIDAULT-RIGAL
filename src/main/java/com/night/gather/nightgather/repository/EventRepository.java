package com.night.gather.nightgather.repository;

import com.night.gather.nightgather.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
