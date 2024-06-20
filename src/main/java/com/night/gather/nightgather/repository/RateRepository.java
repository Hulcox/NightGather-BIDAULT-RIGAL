package com.night.gather.nightgather.repository;

import com.night.gather.nightgather.dataInitializer.DataInitializer;
import com.night.gather.nightgather.entity.Rate;
import com.night.gather.nightgather.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RateRepository extends JpaRepository<Rate, Long> {

    Logger log = LoggerFactory.getLogger(DataInitializer.class);

    long count();

    @Override
    default <S extends Rate> S save(S entity) {
        setGlobalRating(entity);
        return save(entity);
    }

    @Override
    default <S extends Rate> List<S> saveAll(Iterable<S> entities) {
        entities.forEach(RateRepository::setGlobalRating);
        return saveAll(entities);
    }

    private static <S extends Rate> void setGlobalRating(S entity) {
        try {
            User user = entity.getUser();
            List<Rate> rates = entity.getUser().getRates();
            double avg;
            if (rates != null) {
                int totalRates = rates.size();
                double globalRating = user.getGlobalRating();
                avg = globalRating + (double) entity.getRating() / (totalRates + 1);
            } else {
                avg = entity.getRating();
            }
//            log.info(String.valueOf(avg));
            user.setGlobalRating(avg);
        } catch (Exception e) {
            log.error("Error setting global rating: " + e.getMessage(), e);
        }
    }

}
