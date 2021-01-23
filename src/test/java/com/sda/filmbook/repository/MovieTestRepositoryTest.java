package com.sda.filmbook.repository;

import com.sda.filmbook.model.Copy;
import com.sda.filmbook.model.Genre;
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
public class MovieTestRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private CopyRepository copyRepository;

    @Test
    public void saves_movie_and_related_copies() {
        //given
        String title = "Ogniem i Mieczem";

        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setGenre(Genre.DRAMA);
        movie.setDescription("aaa");

        //create related copies
        Copy copy = new Copy();
        copy.setMovie(movie);
        Copy copy2 = new Copy();
        copy2.setMovie(movie);

        List<Copy> copies = new ArrayList<>();
        copies.add(copy);
        copies.add(copy2);
        //add copies list to movie
        movie.setCopies(copies);

        Optional<Movie> foundMovieOptional = movieRepository.findByTitle(title);
        Assertions.assertThat(foundMovieOptional.isEmpty()).isTrue();

        //when
        movieRepository.save(movie);
        foundMovieOptional = movieRepository.findByTitle(title);
        Assertions.assertThat(foundMovieOptional.isPresent()).isTrue();
        Movie foundMovie = foundMovieOptional.get();

        List<Copy> foundCopies = copyRepository.findAll();

        //then
        Assertions.assertThat(foundMovie.getTitle()).isEqualTo(movie.getTitle());
        Assertions.assertThat(foundMovie.getReleaseDate()).isEqualTo(movie.getReleaseDate());

        Assertions.assertThat(foundCopies.isEmpty()).isFalse();
    }

}
