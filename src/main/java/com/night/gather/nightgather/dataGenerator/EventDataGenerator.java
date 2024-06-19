package com.night.gather.nightgather.dataGenerator;

import com.github.javafaker.Faker;
import com.night.gather.nightgather.entity.Event;
import com.night.gather.nightgather.entity.Type;
import com.night.gather.nightgather.entity.User;

import java.util.Collections;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EventDataGenerator {

    private static final Faker faker = new Faker();
    private static final Random random = new Random();

    public static List<Event> generateEvents(int count, List<User> users, List<Type> types) {
        List<Event> events = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Event event = new Event();
            event.setAddress(faker.address().fullAddress());
            event.setNumberOfPlaces(faker.number().numberBetween(10, 100));
            event.setPrice(faker.number().randomDouble(2, 5, 15));
            event.setDatetime(LocalDateTime.now().plusDays(faker.number().numberBetween(1, 30)));
            event.setType(types.get(faker.number().numberBetween(0, types.size() - 1)));
            event.setOrganizer(users.get(faker.number().numberBetween(0, users.size() - 1)));
            events.add(event);
        }
        return events;
    }


}
