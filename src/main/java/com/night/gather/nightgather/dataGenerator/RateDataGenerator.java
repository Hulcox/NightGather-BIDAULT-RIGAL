package com.night.gather.nightgather.dataGenerator;

import com.github.javafaker.Faker;
import com.night.gather.nightgather.entity.Rate;
import com.night.gather.nightgather.entity.User;

import java.util.ArrayList;
import java.util.List;

public class RateDataGenerator {

    private static final Faker faker = new Faker();

    public static List<Rate> generateRates(int count, List<User> users) {
        List<Rate> rates = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Rate rate = new Rate();
            rate.setUser(users.get(faker.number().numberBetween(0, users.size() - 1)));
            rate.setRating(faker.number().numberBetween(1, 5));
            rate.setComment(faker.lorem().sentence());
            rates.add(rate);
        }
        return rates;
    }
}