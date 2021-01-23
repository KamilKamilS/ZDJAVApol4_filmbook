package com.sda.filmbook.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.filmbook.model.Genre;
import com.sda.filmbook.model.Movie;
import com.sda.filmbook.service.MovieService;
import com.sda.filmbook.service.exception.MovieAlreadyExistsInCatalogueException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MovieController.class)
public class MovieControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    @Test
    public void shouldReturnCreatedMovie() throws Exception {
        //initialize movie entity
        Movie movie = new Movie();
        movie.setTitle("TTT");
        movie.setGenre(Genre.ACTION);
        movie.setDescription("AAA");

        //map entity to json
        ObjectMapper objectMapper = new ObjectMapper();
        var movieJson = objectMapper.writeValueAsString(movie);

        Movie movieFromJson = objectMapper.readValue(movieJson, Movie.class);

        //mock service used by controller
        when(movieService.addMovieToCatalogue(movieFromJson)).thenReturn(movie);

        this.mockMvc
                .perform(
                        post("/movie")
                        .content(movieJson)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())     //zgodnie ze sztuka controller w tym wypadku powinien zwracac 201 - created
                .andExpect(jsonPath("$.title").value("TTT"))
                .andExpect(jsonPath("$.genre").value(Genre.ACTION.name()))
                .andExpect(jsonPath("$.description").value("AAA"))
                .andReturn();

    }

}
