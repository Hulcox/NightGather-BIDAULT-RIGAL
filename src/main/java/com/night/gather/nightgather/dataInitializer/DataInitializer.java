package com.night.gather.nightgather.dataInitializer;

import com.night.gather.nightgather.dataGenerator.*;
import com.night.gather.nightgather.entity.Event;
import com.night.gather.nightgather.entity.Rate;
import com.night.gather.nightgather.entity.Type;
import com.night.gather.nightgather.entity.User;
import com.night.gather.nightgather.repository.EventRepository;
import com.night.gather.nightgather.repository.RateRepository;
import com.night.gather.nightgather.repository.TypeRepository;
import com.night.gather.nightgather.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private RateRepository rateRepository;

    @Autowired
    private TypeRepository typeRepository;

    @Override
    public void run(String... args) {
        eventRepository.deleteAll();
        typeRepository.deleteAll();
        rateRepository.deleteAll();
        userRepository.deleteAll();
        try {
            if (typeRepository.count() + userRepository.count() + eventRepository.count() + rateRepository.count() == 0) {
                List<Type> types = initDataType();
                List<User> users = initDataUser();
                List<Event> events = initDataEvent(users, types);
                initDataRate(users);
                initParticipantsEvents(users, events);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private List<Type> initDataType() {
        List<Type> types = TypeDataGenerator.generateTypes();
        typeRepository.saveAll(types);
        log.info("Types saved.");
        return types;
    }

    private List<User> initDataUser() {
        List<User> users = UserDataGenerator.generateUsers(20);
        userRepository.saveAll(users);
        log.info("Users saved.");
        return users;
    }

    private List<Event> initDataEvent(List<User> users, List<Type> types) {
        List<Event> events = EventDataGenerator.generateEvents(20, users, types);
        eventRepository.saveAll(events);
        log.info("Events saved.");
        return events;
    }

    private void initDataRate(List<User> users) {
        List<Rate> rates = RateDataGenerator.generateRates(5, users);
        rateRepository.saveAll(rates);
        log.info("Rates saved.");
    }

    private void initParticipantsEvents(List<User> users, List<Event> events) {
        List<Event> eventsModified = ParticipantsEventsDataGenerator.generateParticipantsEvents(users, events);
        eventRepository.saveAll(eventsModified);
        log.info("Participants and Events relationships saved.");
    }
}
