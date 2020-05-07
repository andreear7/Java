package com.game.restservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class RestServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestServicesApplication.class, args);

        System.out.println("Am pornit!");
    }
}
