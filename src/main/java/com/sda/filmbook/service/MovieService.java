package com.sda.filmbook.service;

import com.sda.filmbook.model.Movie;
import com.sda.filmbook.repository.MovieRepository;
import com.sda.filmbook.service.exception.MovieAlreadyExistsInCatalogueException;
import com.sda.filmbook.service.exception.MovieNotFoundInCatalogueException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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

    public Movie readMovieFromCatalogue(String title) throws MovieNotFoundInCatalogueException {
        return movieRepository.findByTitle(title)
                .orElseThrow(() -> new MovieNotFoundInCatalogueException(title));
    }
}
