package com.sda.filmbook.service;

import com.sda.filmbook.model.Genre;
import com.sda.filmbook.model.Movie;
import com.sda.filmbook.model.Rate;
import com.sda.filmbook.repository.MovieRepository;
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

        rateService.addNewRate(movieFromCatalogue.getMovieId(), rate2);

        Movie movieFromCatalogue2 = movieService.readMovieFromCatalogue(testMovie.getTitle());

        assertThat(movieFromCatalogue2.getRates().size()).isEqualTo(2);

        Rate rate3 = new Rate();
        rate3.setRate(8);
        rate3.setDescription("Test description");
        rate3.setMovie(testMovie);

        rateService.addNewRate(movieFromCatalogue.getMovieId(), rate3);

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
        rate2.setRate(3);
        rate2.setDescription("Test descriptionn");
        rate2.setMovie(testMovie);

        movieService.addMovieToCatalogue(testMovie);
        Movie movieFromCatalogue = movieService.readMovieFromCatalogue(testMovie.getTitle());
        // when

        Rate addedRate = rateService.addNewRate(movieFromCatalogue.getMovieId(), rate);
        assertThat(addedRate).isNotNull();

        Movie movieFromCatalogue1 = movieService.readMovieFromCatalogue(testMovie.getTitle());
        rateService.addNewRate(movieFromCatalogue.getMovieId(), rate2);

        Movie movieFromCatalogue2 = movieService.readMovieFromCatalogue(testMovie.getTitle());
        // then
        assertThat(movieFromCatalogue2.getRates().size()).isEqualTo(2);
    }

}
