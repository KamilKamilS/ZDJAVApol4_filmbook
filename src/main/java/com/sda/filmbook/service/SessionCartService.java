package com.sda.filmbook.service;

import com.sda.filmbook.model.Movie;
import com.sda.filmbook.repository.CartService;
import com.sda.filmbook.repository.MovieRepository;
import com.sda.filmbook.service.exception.MovieNotFoundInCatalogueException;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

@AllArgsConstructor
@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION,
        proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionCartService implements CartService {

    private SessionCart sessionCart;
    private MovieRepository movieRepository;

    @Override
    public void addMovieToCart(Movie movie) throws MovieNotFoundInCatalogueException {
        if (movieRepository.findByTitle(movie.getTitle()).isEmpty()) {
            throw new MovieNotFoundInCatalogueException(movie.getTitle());
        } else {
            sessionCart.addMovie(movie, sessionCart.getQuantities(movie));
        }
    }

    @Override
    public SessionCart getSessionCart() {
        return sessionCart;
    }
}
