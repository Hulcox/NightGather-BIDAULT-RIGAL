package com.night.gather.nightgather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class NightgatherApplication {

	public static void main(String[] args) {
		SpringApplication.run(NightgatherApplication.class, args);
	}

}
