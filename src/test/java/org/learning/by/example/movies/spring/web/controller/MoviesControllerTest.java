package org.learning.by.example.movies.spring.web.controller;

import org.junit.jupiter.api.Test;
import org.learning.by.example.movies.spring.web.model.Movie;
import org.learning.by.example.movies.spring.web.repositories.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class MoviesControllerTest {

    private static final String ACTION_GENRE = "Action";
    private static final String SCI_FI_GENRE = "Sci-Fi";
    private static final String COMEDY_GENRE = "Comedy";

    private static final int FIRST_MOVIE_ID = 1;
    private static final String FIRST_MOVIE_TITLE = "Big Movie";
    private static final int FIRST_MOVIE_YEAR = 2019;

    private static final int SECOND_MOVIE_ID = 2;
    private static final String SECOND_MOVIE_TITLE = "Little Movie";
    private static final int SECOND_MOVIE_YEAR = 2018;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MoviesRepository moviesRepository;

    @Test
    public void weCouldGetMovies() throws Exception {
        final List<Movie> movieList = Arrays.asList(
                new Movie(FIRST_MOVIE_ID, FIRST_MOVIE_TITLE, FIRST_MOVIE_YEAR, Arrays.asList(ACTION_GENRE, SCI_FI_GENRE)),
                new Movie(SECOND_MOVIE_ID, SECOND_MOVIE_TITLE, SECOND_MOVIE_YEAR, Arrays.asList(SCI_FI_GENRE, COMEDY_GENRE))
        );
        when(moviesRepository.findByGenre(anyString())).thenReturn(movieList);

        this.mockMvc.perform(get("/movies/sci-fi"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$[0].id", is(FIRST_MOVIE_ID)))
                .andExpect(jsonPath("$[0].title", is(FIRST_MOVIE_TITLE)))
                .andExpect(jsonPath("$[0].year", is(FIRST_MOVIE_YEAR)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$[1].id", is(SECOND_MOVIE_ID)))
                .andExpect(jsonPath("$[1].title", is(SECOND_MOVIE_TITLE)))
                .andExpect(jsonPath("$[1].year", is(SECOND_MOVIE_YEAR)));

        reset(moviesRepository);
    }

    @Test
    public void weShouldGetAndEmptyArray() throws Exception {
        final List<Movie> movieList = Collections.emptyList();
        when(moviesRepository.findByGenre(anyString())).thenReturn(movieList);

        this.mockMvc.perform(get("/movies/sci-fi"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().string("[]"));

        reset(moviesRepository);
    }
}