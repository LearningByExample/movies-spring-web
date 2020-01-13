package org.learning.by.example.movies.spring.web.repositories;

import org.learning.by.example.movies.spring.web.mapper.MovieMapper;
import org.learning.by.example.movies.spring.web.model.Movie;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.DefaultQueryMappingConfiguration;

@Configuration
public class MappingConfiguration extends DefaultQueryMappingConfiguration {
    MappingConfiguration(final MovieMapper movieMapper) {
        super();
        registerRowMapper(Movie.class, movieMapper);
    }
}
