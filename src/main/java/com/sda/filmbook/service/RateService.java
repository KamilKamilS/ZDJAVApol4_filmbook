package com.sda.filmbook.service;

import com.sda.filmbook.model.Movie;
import com.sda.filmbook.model.Rate;
import com.sda.filmbook.repository.MovieRepository;
import com.sda.filmbook.service.exception.MovieNotFoundInCatalogueException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class RateService {

    MovieRepository movieRepository;

    public List<Rate> addNewRate(Movie movie, Rate rate) throws MovieNotFoundInCatalogueException {
        if (movieRepository.findByTitle(movie.getTitle()).isEmpty()) {
            throw new MovieNotFoundInCatalogueException(movie.getTitle());
        } else {
            Movie movieFromCatalogue = movieRepository.findByTitle(movie.getTitle()).get();
            List<Rate> rateList = movieFromCatalogue.getRates();
            rateList.add(rate);
            movieFromCatalogue.setRates(rateList);
            movieRepository.saveAndFlush(movieFromCatalogue);
            return rateList;
            // TODO - corect???
        }
    }
}
