package com.sda.filmbook.repository;

import com.sda.filmbook.model.Copy;
import com.sda.filmbook.model.Movie;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DataJpaTest    //testujemy tylko warstwe persystencji
public class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private CopyRepository copyRepository;

    @Test
    public void saves_movie_and_related_copies() {
        //given
        String title = "Ogniem i Mieczem";

        Movie m1 = new Movie();
        m1.setTitle(title);
        m1.setReleaseDate(LocalDate.of(1999, 2, 11));

        //create related copies
        Copy c1 = new Copy();
        c1.setMovie(m1);
        Copy c2 = new Copy();
        c2.setMovie(m1);

        List<Copy> copies = new ArrayList<>();
        copies.add(c1);
        copies.add(c2);
        //add copies list to movie
        m1.setCopies(copies);

        Optional<Movie> foundMovieOptional = movieRepository.findByTitle(title);
        Assertions.assertThat(foundMovieOptional.isEmpty()).isTrue();

        //when
        movieRepository.save(m1);
        foundMovieOptional = movieRepository.findByTitle(title);
        Assertions.assertThat(foundMovieOptional.isPresent()).isTrue();
        Movie foundMovie = foundMovieOptional.get();

        List<Copy> foundCopies = copyRepository.findAll();

        //then
        Assertions.assertThat(foundMovie.getTitle()).isEqualTo(m1.getTitle());
        Assertions.assertThat(foundMovie.getReleaseDate()).isEqualTo(m1.getReleaseDate());

        Assertions.assertThat(foundCopies.isEmpty()).isFalse();
    }

}
