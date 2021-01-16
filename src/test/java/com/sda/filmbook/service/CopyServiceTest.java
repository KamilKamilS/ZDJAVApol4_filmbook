package com.sda.filmbook.service;

import com.sda.filmbook.model.Copy;
import com.sda.filmbook.model.Genre;
import com.sda.filmbook.model.Movie;
import com.sda.filmbook.repository.CopyRepository;
import com.sda.filmbook.repository.MovieRepository;
import com.sda.filmbook.service.exception.MovieAlreadyExistsInCatalogueException;
import com.sda.filmbook.service.exception.MovieNotFoundInCatalogueException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CopyServiceTest {

    @Autowired
    CopyService copyService;

    @MockBean
    CopyRepository copyRepository;

    @MockBean
    MovieRepository movieRepository;


    @Test
    public void shouldCreateNewCopyOfMovie() throws MovieNotFoundInCatalogueException {
        // given
        Movie movie = new Movie();
        movie.setTitle("Some title");
        Copy testCopy = new Copy();
        testCopy.setMovie(movie);

        when(movieRepository.findById(any())).thenReturn(Optional.of(movie));
        when(copyRepository.saveAndFlush(any())).thenReturn(testCopy);

        // when
        Copy createdCopy = copyService.createNewCopy(movie);

        // then
        Assertions.assertThat(createdCopy).isEqualTo(testCopy);
    }

    @Test
    public void shouldNotCreateCopyOfNonExistingMovie() throws MovieNotFoundInCatalogueException {
        // given
        Movie movie = new Movie();
        movie.setTitle("Some title");

        // when

        // then
        Assertions.assertThatExceptionOfType(MovieNotFoundInCatalogueException.class)
                .isThrownBy(() -> copyService.createNewCopy(movie));
    }
}
