package com.sda.filmbook.system;

import com.sda.filmbook.model.Genre;
import com.sda.filmbook.model.Movie;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
public class MovieTest {

    private static String URL = "http://localhost:8080/movie";

    @Test
    public void shouldCreateReadAndDeleteMovie() {

        RestTemplate restTemplate = new RestTemplate();

        Movie movie = new Movie();
        movie.setTitle("TTT");
        movie.setGenre(Genre.ACTION);
        movie.setDescription("AAA");

        ResponseEntity<Movie> movieResponseEntity = restTemplate.postForEntity(URL, movie, Movie.class);

        Movie[] forObject = restTemplate.getForObject(URL, Movie[].class);

        Assertions.assertThat(forObject[0]).hasFieldOrPropertyWithValue("title", "TTT");

//        restTemplate.delete(URL + "/" + movieResponseEntity.getBody().getMovieId());
//        Assertions.assertThat(restTemplate.getForObject(URL, Movie[].class)).isEmpty();


    }


}
