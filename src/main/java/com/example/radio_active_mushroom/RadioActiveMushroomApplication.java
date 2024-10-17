package com.example.radio_active_mushroom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
//@EnableMongoRepositories(basePackages = "com.example.radio_active_mushroom.repo.documents")
//@EnableJpaRepositories(basePackages = "com.example.radio_active_mushroom.repo.entity")
public class RadioActiveMushroomApplication {

    public static void main(String[] args) {
        SpringApplication.run(RadioActiveMushroomApplication.class, args);
    }

}
