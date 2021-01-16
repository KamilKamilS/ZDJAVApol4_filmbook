package com.sda.filmbook.service;

import com.sda.filmbook.model.Copy;
import com.sda.filmbook.model.Movie;
import com.sda.filmbook.repository.CopyRepository;
import com.sda.filmbook.repository.MovieRepository;
import com.sda.filmbook.service.exception.MovieNotFoundInCatalogueException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CopyService {

    MovieRepository movieRepository;
    CopyRepository copyRepository;

    public Copy createNewCopy(Movie movie) throws MovieNotFoundInCatalogueException {
        if(movieRepository.findById(movie.getMovieId()).isEmpty()) {
            throw new MovieNotFoundInCatalogueException(movie.getTitle());
        } else {
            Copy copy = new Copy();
            copy.setMovie(movie);
            return copyRepository.saveAndFlush(copy);
        }
    }
}
