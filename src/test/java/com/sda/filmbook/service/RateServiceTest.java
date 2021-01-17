package com.sda.filmbook.service;

import com.sda.filmbook.model.Genre;
import com.sda.filmbook.model.Movie;
import com.sda.filmbook.model.Rate;
import com.sda.filmbook.repository.MovieRepository;
import com.sda.filmbook.service.exception.MovieAlreadyExistsInCatalogueException;
import com.sda.filmbook.service.exception.MovieNotFoundInCatalogueException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class RateServiceTest {

    @Autowired
    RateService rateService;

    @Autowired
    MovieService movieService;

    @Autowired
    MovieRepository movieRepository;

    @Test
    public void shouldAddNewMovieAndThenUpdateRateToIt() throws MovieAlreadyExistsInCatalogueException, MovieNotFoundInCatalogueException {
        // given
        Movie testMovie = new Movie();
        testMovie.setTitle("Ogniem i Mieczem");
        testMovie.setGenre(Genre.DRAMA);

        Rate rate = new Rate();
        rate.setRate(7);
        rate.setDescription("Test description");
        rate.setMovie(testMovie);

        testMovie.getRates().add(rate);
        movieService.addMovieToCatalogue(testMovie);
//        rateService.addNewRate(testMovie, rate);

        // when


        Movie movieFromCatalogue = movieService.readMovieFromCatalogue(testMovie.getTitle());

        // then
        assertThat(movieFromCatalogue).isNotNull();
        assertThat(movieFromCatalogue.getMovieId()).isNotNull();
        assertThat(movieFromCatalogue.getTitle()).isEqualTo(testMovie.getTitle());
        assertThat(movieFromCatalogue.getRates().size()).isEqualTo(1);

        Rate rate2 = new Rate();
        rate2.setRate(7);
        rate2.setDescription("Test description");
        rate2.setMovie(testMovie);

        rateService.addNewRate(movieFromCatalogue, rate2);

        Movie movieFromCatalogue2 = movieService.readMovieFromCatalogue(testMovie.getTitle());
        assertThat(movieFromCatalogue2.getRates().size()).isEqualTo(2);
    }

}
