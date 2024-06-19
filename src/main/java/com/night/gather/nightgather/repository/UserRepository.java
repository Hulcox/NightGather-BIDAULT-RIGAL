package com.night.gather.nightgather.repository;

import com.night.gather.nightgather.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    long count();

    Page<User> findAll(Pageable pageable);
}
