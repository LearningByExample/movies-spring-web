package org.learning.by.example.movies.spring.web.repositories;

import org.learning.by.example.movies.spring.web.model.Movie;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoviesRepository extends CrudRepository<Movie, Integer> {
    @Query(value = "SELECT \n" +
            "  m.id, m.title, m.genres \n" +
            " FROM \n" +
            "  movies as m \n" +
            " WHERE :genre = ANY(string_to_array(LOWER(m.genres),'|')) \n")
    List<Movie> findByGenre(String genre);
}
