package com.sda.filmbook.service;

import com.sda.filmbook.model.Genre;
import com.sda.filmbook.model.Movie;
import com.sda.filmbook.model.Rate;
import com.sda.filmbook.repository.MovieRepository;
import com.sda.filmbook.service.exception.MovieAlreadyExistsInCatalogueException;
import com.sda.filmbook.service.exception.MovieNotFoundInCatalogueException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.annotation.DirtiesContext.*;

@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
public class RateServiceTest {

    @Autowired
    RateService rateService;

    @Autowired
    MovieService movieService;

    @Autowired
    MovieRepository movieRepository;


    @Test
    public void shouldAddNewMovieWithRatesToCatalogue() throws Throwable {
        // given
        Movie testMovie = new Movie();
        testMovie.setTitle("Ogniem i Mieczem");
        testMovie.setGenre(Genre.DRAMA);
        testMovie.setDescription("description");

        Rate rate = new Rate();
        rate.setRate(7);
        rate.setDescription("Test description");
        rate.setMovie(testMovie);

        testMovie.getRates().add(rate);
        movieService.addMovieToCatalogue(testMovie);

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

        Rate rate3 = new Rate();
        rate2.setRate(8);
        rate2.setDescription("Test description");
        rate2.setMovie(testMovie);

        rateService.addNewRate(movieFromCatalogue, rate2);

        Movie movieFromCatalogue3 = movieService.readMovieFromCatalogue(testMovie.getTitle());

        assertThat(movieFromCatalogue3.getRates().size()).isEqualTo(3);
    }


    @Test
    public void shouldAddNewRatesToMovieInCatalogue() throws Throwable{
        // given
        Movie testMovie = new Movie();
        testMovie.setTitle("Ogniem i Mieczem");
        testMovie.setGenre(Genre.DRAMA);
        testMovie.setDescription("description");

        Rate rate = new Rate();
        rate.setRate(7);
        rate.setDescription("Test description");
        rate.setMovie(testMovie);

        Rate rate2 = new Rate();
        rate.setRate(3);
        rate.setDescription("Test descriptionn");
        rate.setMovie(testMovie);

        movieService.addMovieToCatalogue(testMovie);
        Movie movieFromCatalogue = movieService.readMovieFromCatalogue(testMovie.getTitle());
        // when

        List<Rate> rateList = rateService.addNewRate(movieFromCatalogue, rate);
        assertThat(rateList.size()).isEqualTo(1);

        Movie movieFromCatalogue1 = movieService.readMovieFromCatalogue(testMovie.getTitle());
        rateService.addNewRate(movieFromCatalogue, rate2);

        Movie movieFromCatalogue2 = movieService.readMovieFromCatalogue(testMovie.getTitle());
        // then
        assertThat(movieFromCatalogue2.getRates().size()).isEqualTo(2);
    }

}
