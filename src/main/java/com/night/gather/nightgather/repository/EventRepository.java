package com.night.gather.nightgather.repository;

import com.night.gather.nightgather.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EventRepository extends JpaRepository<Event, Long> {

    long count();

    Page<Event> findAll(Pageable pageable);

    @Query("SELECT e FROM Event e LEFT JOIN e.participants p GROUP BY e HAVING COUNT(p) < e.numberOfPlaces ORDER BY e.datetime DESC")
    Page<Event> findAllOrderByDate(Pageable pageable);

//    @Query(value = "SELECT  e.*, u.username, u.firstname, u.lastname FROM  events e JOIN  participants_events pe ON e.id_event = pe.event_id JOIN  users u ON pe.user_id = u.id_user WHERE  e.id_event = :id", nativeQuery = true)
//    Optional<Event> findById(Long id);
}
