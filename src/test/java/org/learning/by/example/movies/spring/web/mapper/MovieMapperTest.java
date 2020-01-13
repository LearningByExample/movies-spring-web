package org.learning.by.example.movies.spring.web.mapper;

import org.junit.jupiter.api.Test;
import org.learning.by.example.movies.spring.web.model.Movie;

import java.sql.ResultSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MovieMapperTest {

    @Test
    void weShouldMapANormalRow() throws Exception {
        final ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getString("title")).thenReturn("this is a test (2000)");
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("genres")).thenReturn("one|two");

        final MovieMapper mapper = new MovieMapper();
        final Movie movie = mapper.mapRow(resultSet, 1);

        assertThat(movie).isNotNull();
        assertThat(movie.getId()).isEqualTo(1);
        assertThat(movie.getTitle()).isEqualTo("this is a test");
        assertThat(movie.getYear()).isEqualTo(2000);
        final List<String> genres = movie.getGenres();
        assertThat(genres).hasSize(2);
        assertThat(genres).contains("one");
        assertThat(genres).contains("two");
    }

    @Test
    void weShouldMapARowWithNoYear() throws Exception {
        final ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getString("title")).thenReturn("this is a test");
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("genres")).thenReturn("one|two");

        final MovieMapper mapper = new MovieMapper();
        final Movie movie = mapper.mapRow(resultSet, 1);

        assertThat(movie).isNotNull();
        assertThat(movie.getId()).isEqualTo(1);
        assertThat(movie.getTitle()).isEqualTo("this is a test");
        assertThat(movie.getYear()).isEqualTo(1900);
        final List<String> genres = movie.getGenres();
        assertThat(genres).hasSize(2);
        assertThat(genres).contains("one");
        assertThat(genres).contains("two");
    }

    @Test
    void weShouldMapARowWithOneGenre() throws Exception {
        final ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getString("title")).thenReturn("this is a test (2000)");
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("genres")).thenReturn("one");

        final MovieMapper mapper = new MovieMapper();
        final Movie movie = mapper.mapRow(resultSet, 1);

        assertThat(movie).isNotNull();
        assertThat(movie.getId()).isEqualTo(1);
        assertThat(movie.getTitle()).isEqualTo("this is a test");
        assertThat(movie.getYear()).isEqualTo(2000);
        final List<String> genres = movie.getGenres();
        assertThat(genres).hasSize(1);
        assertThat(genres).contains("one");
    }

    @Test
    void weShouldMapARowWithNoGenres() throws Exception {
        final ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getString("title")).thenReturn("this is a test (2000)");
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("genres")).thenReturn("");

        final MovieMapper mapper = new MovieMapper();
        final Movie movie = mapper.mapRow(resultSet, 1);

        assertThat(movie).isNotNull();
        assertThat(movie.getId()).isEqualTo(1);
        assertThat(movie.getTitle()).isEqualTo("this is a test");
        assertThat(movie.getYear()).isEqualTo(2000);
        final List<String> genres = movie.getGenres();
        assertThat(genres).hasSize(0);
        assertThat(genres).isEmpty();
    }

    @Test
    void weShouldMapATitleWithAlternativeTitle() throws Exception {
        final ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getString("title")).thenReturn("this is a test (alternative title) (2000)");
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("genres")).thenReturn("one|two");

        final MovieMapper mapper = new MovieMapper();
        final Movie movie = mapper.mapRow(resultSet, 1);

        assertThat(movie).isNotNull();
        assertThat(movie.getId()).isEqualTo(1);
        assertThat(movie.getTitle()).isEqualTo("this is a test (alternative title)");
        assertThat(movie.getYear()).isEqualTo(2000);
        final List<String> genres = movie.getGenres();
        assertThat(genres).hasSize(2);
        assertThat(genres).contains("one");
        assertThat(genres).contains("two");
    }

    @Test
    void weShouldMapATitleWithAlternativeTitleAndNoYear() throws Exception {
        final ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getString("title")).thenReturn("this is a test (alternative title)");
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("genres")).thenReturn("one|two");

        final MovieMapper mapper = new MovieMapper();
        final Movie movie = mapper.mapRow(resultSet, 1);

        assertThat(movie).isNotNull();
        assertThat(movie.getId()).isEqualTo(1);
        assertThat(movie.getTitle()).isEqualTo("this is a test (alternative title)");
        assertThat(movie.getYear()).isEqualTo(1900);
        final List<String> genres = movie.getGenres();
        assertThat(genres).hasSize(2);
        assertThat(genres).contains("one");
        assertThat(genres).contains("two");
    }
}