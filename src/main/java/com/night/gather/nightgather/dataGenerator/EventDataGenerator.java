package com.night.gather.nightgather.dataGenerator;

import com.github.javafaker.Faker;
import com.night.gather.nightgather.entity.Event;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventDataGenerator {

    private static final Faker faker = new Faker();

    public static List<Event> generateEvents(int count) {
        List<Event> events = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Event event = new Event();
            event.setAddress(faker.address().fullAddress());
            event.setNumberOfPlaces(faker.number().numberBetween(10, 100));
            event.setPrice(faker.number().randomDouble(2, 5, 15));
            event.setDatetime(LocalDateTime.now().plusDays(faker.number().numberBetween(1, 30)));
            events.add(event);
        }
        return events;
    }
}
