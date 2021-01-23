package com.sda.filmbook.controller;

import com.sda.filmbook.model.Genre;
import com.sda.filmbook.model.Movie;
import com.sda.filmbook.service.exception.MovieAlreadyExistsInCatalogueException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.util.List;


@SpringBootTest
class MovieTestControllerTest {

    @Autowired
    MovieController movieController;

    @Test
    public void shouldCreateReadDelete() throws Throwable {

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

    @Test
    public void shouldCreateTwoMoviesAndThenQueryByGenreAndReleaseDate() throws MovieAlreadyExistsInCatalogueException {
        Movie movie = new Movie();
        movie.setTitle("test1");
        movie.setGenre(Genre.DRAMA);
        movie.setDescription("some description1");
        movie.setReleaseDate(LocalDate.of(2020,10,10));

        Movie movie1 = new Movie();
        movie1.setTitle("test2");
        movie1.setGenre(Genre.DRAMA);
        movie1.setDescription("some description2");
        movie1.setReleaseDate(LocalDate.of(2019,10,10));

        Movie movie2 = new Movie();
        movie2.setTitle("test3");
        movie2.setGenre(Genre.THRILLER);
        movie2.setDescription("some description3");
        movie2.setReleaseDate(LocalDate.of(2018,10,10));


        movieController.add(movie);
        movieController.add(movie1);
        movieController.add(movie2);

        List<Movie> movieByQuery = movieController.findByQuery(Genre.DRAMA, null, null);
        Assertions.assertThat(movieByQuery.size()).isEqualTo(2);

        List<Movie> byQuery = movieController.findByQuery(null, LocalDate.of(2010, 10, 10), LocalDate.of(2021, 01, 10));

        Assertions.assertThat(byQuery.size()).isEqualTo(3);
        Assertions.assertThat(byQuery.get(0)).hasFieldOrPropertyWithValue("title", "test1");
    }

    @Test
    public void shouldThrowExceptionWhenAddingMovieWithoutDescription() {
        Movie movie = new Movie();
        movie.setTitle("test1");
        movie.setGenre(Genre.DRAMA);

        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> movieController.add(movie));
    }

    @Test
    public void shouldThrowExceptionWhenAddingMovieWithoutGenre() {
        Movie movie = new Movie();
        movie.setTitle("test1");

        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> movieController.add(movie));
    }

}