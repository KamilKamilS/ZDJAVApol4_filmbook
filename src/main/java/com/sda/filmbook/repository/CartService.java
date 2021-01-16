package com.sda.filmbook.repository;

import com.sda.filmbook.model.Movie;
import com.sda.filmbook.model.Order;
import com.sda.filmbook.model.dto.SessionCart;
import com.sda.filmbook.service.exception.MovieNotFoundInCatalogueException;

public interface CartService {

    void addMovieToCart(Movie movie) throws MovieNotFoundInCatalogueException;

    SessionCart getSessionCart();
}
