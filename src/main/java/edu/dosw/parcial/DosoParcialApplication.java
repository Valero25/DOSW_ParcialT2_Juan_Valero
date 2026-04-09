package edu.dosw.parcial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Main application class for DOSW Parcial T2 project.
 * This is the entry point for the Spring Boot application.
 */
@SpringBootApplication
@ComponentScan(basePackages = "edu.dosw.parcial")
public class DosoParcialApplication {

    public static void main(String[] args) {
        SpringApplication.run(DosoParcialApplication.class, args);
    }
}
