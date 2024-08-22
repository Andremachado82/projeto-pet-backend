package com.andre.sistema_pet.config;

import jakarta.annotation.PostConstruct;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FlywayConfig {

    @Autowired
    private Flyway flyway;

    @PostConstruct
    public void runFlywayMigrations() {
        flyway.migrate();
    }
}
