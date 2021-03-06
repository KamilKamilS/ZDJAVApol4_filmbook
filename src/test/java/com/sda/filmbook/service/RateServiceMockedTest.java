package com.sda.filmbook.service;

import com.sda.filmbook.model.Genre;
import com.sda.filmbook.model.Movie;
import com.sda.filmbook.model.Rate;
import com.sda.filmbook.repository.MovieRepository;
import com.sda.filmbook.service.exception.MovieNotFoundInCatalogueException;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class RateServiceMockedTest {

    @InjectMocks
    @Autowired
    RateService rateService;

    @MockBean
    MovieRepository movieRepository;

    @Test
    public void shouldAddNewRateToGivenMovie() throws MovieNotFoundInCatalogueException {
        // given
        Movie movie = new Movie();
        movie.setTitle("Test title");
        movie.setGenre(Genre.DRAMA);
        movie.setDescription("aaa");

        Rate rate = new Rate();
        rate.setRate(7);
        rate.setDescription("Test description");
        rate.setMovie(movie);

        when(movieRepository.findById(anyLong())).thenReturn(Optional.of(movie));

        // when
        Rate addedRate = rateService.addNewRate(anyLong(), rate);

        // then
        assertThat(addedRate).isNotNull();
    }

}
