package com.sda.filmbook.repository;

import com.sda.filmbook.model.Genre;
import com.sda.filmbook.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    Optional<Movie> findByTitle(String title);
    List<Movie> findMovieByGenreEquals(Genre genre);
    List<Movie> findMovieByReleaseDateAfter(LocalDate date);
    List<Movie> findMovieByReleaseDateBetween(LocalDate initialDate, LocalDate finalDate);
    List<Movie> findMovieByRatesGreaterThan(int rate);
    List<Movie> findMovieByGenreEqualsAndReleaseDateAfter(Genre genre, LocalDate date);
    List<Movie> findMovieByGenreEqualsAndReleaseDateBetween(Genre genre, LocalDate initialDate, LocalDate finalDate);

}
