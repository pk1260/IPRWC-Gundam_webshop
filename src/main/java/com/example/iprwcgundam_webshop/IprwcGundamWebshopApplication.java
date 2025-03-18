package com.example.iprwcgundam_webshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication()
@EnableJpaRepositories
public class IprwcGundamWebshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(IprwcGundamWebshopApplication.class, args);
    }

}
