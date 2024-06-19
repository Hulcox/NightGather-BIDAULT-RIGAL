package com.night.gather.nightgather.dataInitializer;

import com.night.gather.nightgather.dataGenerator.EventDataGenerator;
import com.night.gather.nightgather.entity.Event;
import com.night.gather.nightgather.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventDataInitializer implements CommandLineRunner {

    @Autowired
    private EventRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Event> users = EventDataGenerator.generateEvents(20);
        userRepository.saveAll(users);
    }
}
