package com.night.gather.nightgather.repository;

import com.night.gather.nightgather.entity.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RateRepository extends JpaRepository<Rate, Long> {

    long count();
}
