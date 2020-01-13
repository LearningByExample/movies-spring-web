package org.learning.by.example.movies.spring.web.model;

import java.util.List;

public class Movie {
    private final Integer id;
    private final String title;
    private final Integer year;
    private final List<String> genres;

    public Movie(final Integer id, final String title, final Integer year, List<String> genres) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.genres = genres;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getYear() {
        return year;
    }

    public List<String> getGenres() {
        return genres;
    }
}
