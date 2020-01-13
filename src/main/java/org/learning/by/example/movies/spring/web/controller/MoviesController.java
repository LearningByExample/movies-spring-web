package org.learning.by.example.movies.spring.web.controller;

import org.learning.by.example.movies.spring.web.model.Movie;
import org.learning.by.example.movies.spring.web.service.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MoviesController {
    private final MovieService movieService;

    public MoviesController(final MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies/{genre}")
    List<Movie> getMovies(@PathVariable String genre) {
        return movieService.getMoviesByGenre(genre);
    }
}
