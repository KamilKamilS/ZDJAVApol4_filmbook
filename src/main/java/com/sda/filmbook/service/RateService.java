package com.sda.filmbook.service;

import com.sda.filmbook.model.Movie;
import com.sda.filmbook.model.Rate;
import com.sda.filmbook.repository.MovieRepository;
import com.sda.filmbook.service.exception.MovieNotFoundInCatalogueException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RateService {

    private final MovieRepository movieRepository;

    public Rate addNewRate(Long movieId, Rate rate) throws MovieNotFoundInCatalogueException {
        Optional<Movie> movieInCatalogue = movieRepository.findById(movieId);
        if (movieInCatalogue.isEmpty()) {
            throw new MovieNotFoundInCatalogueException(" ");
        } else {
//            Movie movieFromCatalogue = movieInCatalogue.get();
            List<Rate> rateList = movieInCatalogue.get().getRates();
            rateList.add(rate);
            movieInCatalogue.get().setRates(rateList);
            movieRepository.saveAndFlush(movieInCatalogue.get());
            return rate;
        }
    }

    public List<Rate> getAllRatesOfMovie(Long movieId) throws MovieNotFoundInCatalogueException {
        Optional<Movie> movie = movieRepository.findById(movieId);
        if (movie.isEmpty()) {
            throw new MovieNotFoundInCatalogueException(" ");
        } else {
            List<Rate> rateList = movie.get().getRates();
            return rateList;
        }
    }
}
