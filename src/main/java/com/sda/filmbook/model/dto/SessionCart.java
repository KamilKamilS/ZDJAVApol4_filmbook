package com.sda.filmbook.model.dto;

import com.sda.filmbook.model.Movie;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

@Scope(value = WebApplicationContext.SCOPE_SESSION)
@Component
public class SessionCart {

    private Map<Movie, Integer> movies;

    public SessionCart() {
        this.movies = new HashMap<>();
    }

    public void addMovie(Movie movie, int quantity) {
        movies.put(movie, quantity);
    }

    public Map<Movie, Integer> getMovies() {
        return movies;
    }

    public int getQuantities(Movie movie) {
        return getMovies().get(movie) == null ? 1 : getMovies().get(movie) + 1;
    }
}
