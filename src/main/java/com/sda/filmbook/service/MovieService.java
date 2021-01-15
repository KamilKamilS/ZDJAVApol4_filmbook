package com.sda.filmbook.service;

import com.sda.filmbook.model.Movie;
import com.sda.filmbook.repository.MovieRepository;
import com.sda.filmbook.service.exception.MovieAlreadyExistsInCatalogueException;
import com.sda.filmbook.service.exception.MovieNotFoundInCatalogue;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class MovieService {

    MovieRepository movieRepository;

    public Movie addMovieToCatalogue(Movie movie) throws MovieAlreadyExistsInCatalogueException {
        if (movieRepository.findByTitle(movie.getTitle()).isEmpty()) {
            return movieRepository.save(movie);
        }
        throw new MovieAlreadyExistsInCatalogueException(movie.getTitle());
    }

    public Movie getMovieByTitle(String title) throws MovieNotFoundInCatalogue {
        return movieRepository.findByTitle(title)
                .orElseThrow(() -> new MovieNotFoundInCatalogue(title));
    }
}
