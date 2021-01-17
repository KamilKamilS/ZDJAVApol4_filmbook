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
    public void shouldAddNewMovieAndThenAddRateToIt() throws MovieAlreadyExistsInCatalogueException, MovieNotFoundInCatalogueException {
        // given
        Movie testMovie = Movie.builder()
                .title("Test Title2")
                .genre(Genre.DRAMA)
                .build();
        Rate rate = Rate.builder()
                .rate(7)
                .description("Test desciption")
                .movie(testMovie)
                .build();

        movieService.addMovieToCatalogue(testMovie);

        // when
//        rateService.addNewRate(testMovie, rate);

        Movie movieFromCatalogue = movieService.readMovieFromCatalogue(testMovie.getTitle());

        // then
        assertThat(movieFromCatalogue).isNotNull();




    }
}