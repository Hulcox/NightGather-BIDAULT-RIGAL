package com.night.gather.nightgather.dataGenerator;

import com.night.gather.nightgather.entity.Event;
import com.night.gather.nightgather.entity.User;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ParticipantsEventsDataGenerator {

    private static final Random random = new Random();

    public static List<Event> generateParticipantsEvents(List<User> users, List<Event> events) {
        for (Event event : events) {
            event.setParticipants(selectRandomUsers(users));
        }
        return events;
    }

    public static List<User> selectRandomUsers(List<User> users) {
        Collections.shuffle(users);
        return users.subList(0, random.nextInt(users.size() - 1));
    }
}
