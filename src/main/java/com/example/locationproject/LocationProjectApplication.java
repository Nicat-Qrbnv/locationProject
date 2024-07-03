package com.example.locationproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class LocationProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(LocationProjectApplication.class, args);

    }

}
