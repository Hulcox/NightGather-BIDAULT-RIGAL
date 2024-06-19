package com.night.gather.nightgather.dataInitializer;

import com.night.gather.nightgather.dataGenerator.TypeDataGenerator;
import com.night.gather.nightgather.entity.Type;
import com.night.gather.nightgather.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TypeDataInitializer implements CommandLineRunner {

    @Autowired
    private TypeRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Type> users = TypeDataGenerator.generateTypes();
        userRepository.saveAll(users);
    }
}
