package com.sda.filmbook.service;

import com.sda.filmbook.model.Copy;
import com.sda.filmbook.model.Genre;
import com.sda.filmbook.model.Movie;
import com.sda.filmbook.model.Status;
import com.sda.filmbook.repository.MovieRepository;
import com.sda.filmbook.service.exception.CopyNotFoundException;
import com.sda.filmbook.service.exception.MovieAlreadyExistsInCatalogueException;
import com.sda.filmbook.service.exception.MovieNotFoundInCatalogueException;
import lombok.AllArgsConstructor;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

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

    // TODO - jest ok czy lepeij to zrobic przez copy repository
    public Copy getFreeCopyOfMovie(Movie movie) throws CopyNotFoundException {
        List<Copy> copies = movieRepository.findByTitle(movie.getTitle()).get().getCopies();

        Optional<Copy> availableCopy = copies.stream()
                .filter(copy -> copy.getStatus().equals(Status.AVAILABLE))
                .findFirst();

        if (availableCopy.isPresent()) {
            return availableCopy.get();
        } else {
            throw new CopyNotFoundException(movie.getTitle());
        }
    }


}
