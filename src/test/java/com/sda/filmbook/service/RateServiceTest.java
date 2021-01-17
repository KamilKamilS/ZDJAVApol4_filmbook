package com.sda.filmbook.service;

import com.sda.filmbook.model.Genre;
import com.sda.filmbook.model.Movie;
import com.sda.filmbook.model.Rate;
import com.sda.filmbook.repository.MovieRepository;
import com.sda.filmbook.service.exception.MovieAlreadyExistsInCatalogueException;
import com.sda.filmbook.service.exception.MovieNotFoundInCatalogueException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class RateServiceTest {

    @Autowired
    RateService rateService;

    @Autowired
    MovieService movieService;

    @MockBean
    MovieRepository movieRepository;

    @Test
    public void shouldAddNewRateToGivenMovie() throws MovieNotFoundInCatalogueException {
        // given
        Movie movie = new Movie();
        movie.setTitle("Test title");
        Rate rate = Rate.builder()
                .rate(7)
                .description("Test description")
                .movie(movie)
                .build();
        when(movieRepository.findByTitle(movie.getTitle())).thenReturn(Optional.of(movie));

        // when
        List<Rate> rateList = rateService.addNewRate(movie, rate);

        // then
        assertThat(rateList).isEqualTo(Arrays.asList(rate));
    }

    @Test
    public void shouldAddNewMovieAndThenUpdateRateToIt() throws MovieAlreadyExistsInCatalogueException, MovieNotFoundInCatalogueException {
        // given
        Movie testMovie = Movie.builder()
                .title("Test Title2")
                .genre(Genre.DRAMA)
                .rates(new ArrayList<>())
                .build();
        Rate rate = Rate.builder()
                .rate(7)
                .description("Test desciption")
                .movie(testMovie)
                .build();


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

        rateService.addNewRate(movieFromCatalogue, rate);
        Movie movieFromCatalogue2 = movieService.readMovieFromCatalogue(testMovie.getTitle());

        assertThat(movieFromCatalogue.getRates().size()).isEqualTo(2);






    }
}