package com.sda.filmbook.controller;

import com.sda.filmbook.model.Genre;
import com.sda.filmbook.model.Movie;
import com.sda.filmbook.service.exception.MovieAlreadyExistsInCatalogueException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MovieControllerTest {

    @Autowired
    MovieController movieController;

    @Test
    public void shouldCreatereadDelete() throws Throwable {

        Movie movie = new Movie();
                movie.setTitle("test");
                movie.setGenre(Genre.DRAMA);
                movie.setDescription("some description");

        Movie movieResult = movieController.add(movie);

        List<Movie> readMovie = movieController.getAll();
        Assertions.assertThat(readMovie.get(0)).hasFieldOrPropertyWithValue("title", "test")
                .hasFieldOrPropertyWithValue("genre", Genre.DRAMA);

        movieController.delete(movie.getMovieId());

        Assertions.assertThat(movieController.getAll()).isEmpty();
    }

}