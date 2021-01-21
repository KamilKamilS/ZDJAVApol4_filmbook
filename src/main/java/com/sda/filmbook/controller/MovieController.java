package com.sda.filmbook.controller;

import com.sda.filmbook.model.Movie;
import com.sda.filmbook.service.MovieService;
import com.sda.filmbook.service.exception.MovieAlreadyExistsInCatalogueException;
import com.sda.filmbook.service.exception.MovieNotFoundInCatalogueException;
import com.sda.filmbook.util.FieldErrorMessage;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
public class MovieController {

    MovieService movieService;

    @GetMapping("/movie/premiere")
    public List<Movie> getAllPremiere() {
       return movieService.getPremiereMovies();
    }

//    @GetMapping("/movie")
//    public Movie get(@RequestParam(value = "title") String title) throws MovieNotFoundInCatalogueException {
//        return movieService.readMovieFromCatalogue(title);
//    }

    @GetMapping("/movie")
    public List<Movie> getAll() throws MovieNotFoundInCatalogueException {
        return movieService.readAllMoviesFromCatalogue();
    }


    @PostMapping("/movie")
    public Movie add(@Valid @RequestBody Movie movie) throws MovieAlreadyExistsInCatalogueException {
        return movieService.addMovieToCatalogue(movie);
    }

    @DeleteMapping("movie/{id}")
    public void delete(@PathVariable Long id) {
        movieService.deleteMovieFromCatalogue(id);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    List<FieldErrorMessage> exceptionHandler(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<FieldErrorMessage> fieldErrorMessages = fieldErrors.stream()
                .map(fieldError -> new FieldErrorMessage(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());

        return fieldErrorMessages;
    }

}
