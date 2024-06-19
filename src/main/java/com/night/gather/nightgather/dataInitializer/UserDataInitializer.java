package com.night.gather.nightgather.dataInitializer;

import com.night.gather.nightgather.dataGenerator.UserDataGenerator;
import com.night.gather.nightgather.entity.User;
import com.night.gather.nightgather.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        List<User> users = UserDataGenerator.generateUsers(20);
        userRepository.saveAll(users);
    }
}
