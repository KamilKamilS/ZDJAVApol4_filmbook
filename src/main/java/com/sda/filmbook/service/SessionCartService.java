package com.sda.filmbook.service;

import com.sda.filmbook.model.Movie;
import com.sda.filmbook.repository.CartService;
import com.sda.filmbook.repository.MovieRepository;
import com.sda.filmbook.service.exception.MovieNotFoundInCatalogueException;
import com.sda.filmbook.service.exception.MovieNotFoundInSessionCart;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION,
        proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionCartService implements CartService {


    private Map<Movie, Integer> movies;
    private MovieRepository movieRepository;

    @Override
    public void addMovieToCart(Movie movie) throws MovieNotFoundInCatalogueException {
        if (movieRepository.findByTitle(movie.getTitle()).isEmpty()) {
            throw new MovieNotFoundInCatalogueException(movie.getTitle());
        } else {
            movies.put(movie, this.getQuantities(movie));
        }
    }

    public Movie removeOneCopyOfMovieFromCart(Movie movie) throws MovieNotFoundInSessionCart {
        if (movies.containsKey(movie)) {
            removeOneCopy(movie);
            return movie;
        } else {
            throw new MovieNotFoundInSessionCart(movie.getTitle());
        }
    }

    private void removeOneCopy(Movie movie) {
        int numberOfCopies = movies.get(movie);
        if (numberOfCopies > 1) {
            movies.replace(movie, --numberOfCopies);
        } else {
            movies.remove(movie);
        }
    }

    @Override
    public Map<Movie, Integer> getSessionCart() {
        return movies;
    }

    public Map<Movie, Integer> getMovies() {
        return movies;
    }

    private int getQuantities(Movie movie) {
        return getMovies().get(movie) == null ? 1 : getMovies().get(movie) + 1;
    }
}
