package org.learning.by.example.movies.spring.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@SpringBootApplication
@EnableJdbcRepositories
public class SpringWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringWebApplication.class, args);
    }
}
