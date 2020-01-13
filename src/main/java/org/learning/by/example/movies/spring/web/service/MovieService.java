package org.learning.by.example.movies.spring.web.service;

import org.learning.by.example.movies.spring.web.model.Movie;
import org.learning.by.example.movies.spring.web.repositories.MoviesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    private final MoviesRepository repository;

    public MovieService(final MoviesRepository repository) {
        this.repository = repository;
    }

    public List<Movie> getMoviesByGenre(final String genre) {
        return repository.findByGenre(genre.toLowerCase());
    }
}
