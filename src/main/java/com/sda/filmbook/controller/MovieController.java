package com.sda.filmbook.controller;

import com.sda.filmbook.model.Movie;
import com.sda.filmbook.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class MovieController {

    MovieService movieService;

    @GetMapping("/movie/premiere")
    public List<Movie> getAllPremiere() {
       return movieService.getPremiereMovies();
    }

}
