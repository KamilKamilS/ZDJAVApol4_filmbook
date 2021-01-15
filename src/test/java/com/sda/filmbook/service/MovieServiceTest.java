package com.sda.filmbook.service;

import com.sda.filmbook.model.Genre;
import com.sda.filmbook.model.Movie;
import com.sda.filmbook.repository.MovieRepository;
import com.sda.filmbook.service.exception.MovieAlreadyExistsInCatalogueException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MovieServiceTest {

    @Autowired
    MovieService movieService;

    @MockBean
    MovieRepository movieRepository;

    @Test
    public void shouldAddNewMovieToCatalogue() throws MovieAlreadyExistsInCatalogueException {
        // given
        Movie movie = new Movie();
        movie.setTitle("Some title");
        movie.setGenre(Genre.ACTION);
        when(movieRepository.save(movie)).thenReturn(movie);

        // when
        Movie movieAdded = movieService.addMovieToCatalogue(movie);

        // then
        assertThat(movieAdded).isEqualTo(movie);
    }

    
}