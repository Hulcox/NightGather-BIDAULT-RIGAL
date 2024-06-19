package com.night.gather.nightgather.dataInitializer;

import com.night.gather.nightgather.dataGenerator.RateDataGenerator;
import com.night.gather.nightgather.entity.Rate;
import com.night.gather.nightgather.repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RateDataInitializer implements CommandLineRunner {

    @Autowired
    private RateRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Rate> users = RateDataGenerator.generateRates(50);
        userRepository.saveAll(users);
    }
}
