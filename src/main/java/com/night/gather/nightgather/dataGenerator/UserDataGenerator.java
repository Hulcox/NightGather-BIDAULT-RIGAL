package com.night.gather.nightgather.dataGenerator;

import com.github.javafaker.Faker;
import com.night.gather.nightgather.entity.User;
import com.night.gather.nightgather.security.Password;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserDataGenerator {

    private static final Faker faker = new Faker();

    public static List<User> generateUsers(int count) {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            User user = new User();
            user.setEmail(faker.internet().emailAddress());
            byte[] salt = Password.createSalt();
            String hash = Password.createHash("0000".toCharArray(), salt);
            user.setSalt(Password.toHex(salt));
            user.setPassword(hash);
            user.setLastname(faker.name().lastName());
            user.setFirstname(faker.name().firstName());
            user.setUsername(faker.name().username());
            user.setRegion(faker.address().state());
            user.setAddress(faker.address().streetAddress());
            user.setCity(faker.address().city());
            user.setAge(faker.number().numberBetween(18, 55));
            user.setBio(faker.lorem().sentence());
            users.add(user);
        }
        return users;
    }
}
