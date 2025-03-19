package com.example.iprwcgundam_webshop.seeder;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DatabaseSeeder {
    private final AdminSeeder adminSeeder;
    private final ProductSeeder productSeeder;
    private final Logger logger;

    private boolean alreadySeeded = false;

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        if (alreadySeeded) {
            return;
        }

        logger.info("Starting seeder");
        this.adminSeeder.seed();
        this.productSeeder.seed();
        this.alreadySeeded = true;
    }
}
