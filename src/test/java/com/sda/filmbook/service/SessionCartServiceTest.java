package com.sda.filmbook.service;

import com.sda.filmbook.model.Order;
import com.sda.filmbook.model.Movie;
import com.sda.filmbook.model.dto.SessionCart;
import com.sda.filmbook.repository.CartService;
import com.sda.filmbook.repository.MovieRepository;
import com.sda.filmbook.service.exception.MovieNotFoundInCatalogueException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Map;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class SessionCartServiceTest {

    @Autowired
    CartService cartService;

    @MockBean
    MovieRepository movieRepository;

    @Test
    public void shouldAddOneMovieToCart() throws MovieNotFoundInCatalogueException {
        // given
        Movie testMovie = new Movie();
        testMovie.setTitle("Test movie 1");

        when(movieRepository.findByTitle(anyString())).thenReturn(Optional.of(testMovie));

        // when
        cartService.addMovieToCart(testMovie);
        SessionCart cart = cartService.getSessionCart();

        // then
        Assertions.assertThat(cart.getMovies()).isEqualTo(Map.of(testMovie, 1));
    }

    @Test
    public void shouldAddTwoTheSameMoviesToCart() throws MovieNotFoundInCatalogueException {
        // given
        Movie testMovie = new Movie();
        testMovie.setTitle("Test movie 1");

        when(movieRepository.findByTitle(anyString())).thenReturn(Optional.of(testMovie));

        // when
        cartService.addMovieToCart(testMovie);
        cartService.addMovieToCart(testMovie);
        SessionCart cart = cartService.getSessionCart();

        // then
        Assertions.assertThat(cart.getMovies()).isEqualTo(Map.of(testMovie, 2));
    }


}