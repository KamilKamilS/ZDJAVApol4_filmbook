package com.sda.filmbook.service;

import com.sda.filmbook.model.Genre;
import com.sda.filmbook.model.Movie;
import com.sda.filmbook.repository.MovieRepository;
import com.sda.filmbook.service.exception.MovieAlreadyExistsInCatalogueException;
import com.sda.filmbook.service.exception.MovieNotFoundInCatalogueException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Service
public class MovieService {

    MovieRepository movieRepository;

    public Movie addMovieToCatalogue(Movie movie) throws MovieAlreadyExistsInCatalogueException {
        if (movieRepository.findByTitle(movie.getTitle()).isEmpty()) {
            return movieRepository.saveAndFlush(movie);
        }
        throw new MovieAlreadyExistsInCatalogueException(movie.getTitle());
    }

    public List<Movie> readAllMoviesFromCatalogue() {
        return movieRepository.findAll();
    }

    public Movie readMovieFromCatalogue(String title) throws MovieNotFoundInCatalogueException {
        return movieRepository.findByTitle(title)
                .orElseThrow(() -> new MovieNotFoundInCatalogueException(title));
    }

    public void deleteMovieFromCatalogue(Long id) {
        movieRepository.deleteById(id);
    }

    public List<Movie> getPremiereMovies() {
        return Collections.emptyList();
    }

    public List<Movie> getMoviesByGenre(Genre genre) {
        return movieRepository.findMovieByGenreEquals(genre);
    }

    public List<Movie> getMoviesByReleaseDateBefore(LocalDate date) {
        return movieRepository.findMovieByReleaseDateAfter(date);
    }

    public List<Movie> getMoviesByReleaseDateBetween(LocalDate initialDate, LocalDate finalDate) {
        return movieRepository.findMovieByReleaseDateBetween(initialDate, finalDate);
    }

    public List<Movie> getMoviesWithRatesGraterThan(int rate) {
        return movieRepository.findMovieByRatesGreaterThan(rate);
    }

    public List<Movie> getMoviesByGenreAndReleaseDateBefore(Genre genre, LocalDate date) {
        return movieRepository.findMovieByGenreEqualsAndReleaseDateAfter(genre, date);
    }

    public List<Movie> getMoviesByGenreAndReleaseDateBetween(Genre genre, LocalDate initialDate,  LocalDate finalDate) {
        return movieRepository.findMovieByGenreEqualsAndReleaseDateBetween(genre, initialDate, finalDate);
    }


}
