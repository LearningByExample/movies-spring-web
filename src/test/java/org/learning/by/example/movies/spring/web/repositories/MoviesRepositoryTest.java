package org.learning.by.example.movies.spring.web.repositories;

import org.junit.jupiter.api.Test;
import org.learning.by.example.movies.spring.web.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Testcontainers
@ContextConfiguration(initializers = {MoviesRepositoryTest.Initializer.class})
@Sql({"/sql/schema.sql", "/sql/data.sql"})
class MoviesRepositoryTest {

    @Container
    public static final PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:11.4-alpine")
            .withDatabaseName("movies")
            .withUsername("sa")
            .withPassword("");

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "movies-datasource.connection-string=" + postgreSQLContainer.getJdbcUrl()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    @Autowired
    private MoviesRepository moviesRepository;

    @Test
    public void weCouldGetSciFiMovies() throws Exception {
        final List<Movie> movies = moviesRepository.findByGenre("sci-fi");

        assertThat(movies).isNotEmpty();
        assertThat(movies).hasSize(2);
    }

    @Test
    public void weCouldGetActionMovies() throws Exception {
        final List<Movie> movies = moviesRepository.findByGenre("action");

        assertThat(movies).isNotEmpty();
        assertThat(movies).hasSize(4);
    }

    @Test
    public void weCouldGetFantasyMovies() throws Exception {
        final List<Movie> movies = moviesRepository.findByGenre("fantasy");

        assertThat(movies).isNotEmpty();
        assertThat(movies).hasSize(1);
    }

    @Test
    public void weCouldNotGetAnimationMovies() throws Exception {
        final List<Movie> movies = moviesRepository.findByGenre("animation");

        assertThat(movies).isEmpty();
        assertThat(movies).hasSize(0);
    }

}