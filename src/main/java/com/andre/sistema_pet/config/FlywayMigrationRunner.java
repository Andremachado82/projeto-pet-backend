package com.andre.sistema_pet.config;

import jakarta.annotation.PostConstruct;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class FlywayMigrationRunner {

    @Autowired
    private Flyway flyway;

    @PostConstruct
    public void runFlywayMigrations() {
        flyway.migrate();
    }
}
