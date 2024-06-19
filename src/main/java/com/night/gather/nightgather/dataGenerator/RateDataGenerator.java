package com.night.gather.nightgather.dataGenerator;

import com.github.javafaker.Faker;
import com.night.gather.nightgather.entity.Rate;

import java.util.ArrayList;
import java.util.List;

public class RateDataGenerator {

    private static final Faker faker = new Faker();

    public static List<Rate> generateRates(int count) {
        List<Rate> rates = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Rate rate = new Rate();
            rate.setRating(faker.number().numberBetween(1, 5));
            rate.setComment(faker.lorem().sentence());
            rates.add(rate);
        }
        return rates;
    }
}