package com.sda.filmbook.service;

import com.sda.filmbook.model.Movie;
import com.sda.filmbook.repository.MovieRepository;
import com.sda.filmbook.service.exception.MovieAlreadyExistsInCatalogueException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class MovieService {

    MovieRepository movieRepository;

    public Movie addMovieToCatalogue(Movie movie) throws MovieAlreadyExistsInCatalogueException {
        if (movieRepository.findAll().contains(movie)) {
            return movieRepository.save(movie);
        }
        throw new MovieAlreadyExistsInCatalogueException(movie.getTitle());
    }
}
