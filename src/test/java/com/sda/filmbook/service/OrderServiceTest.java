package com.sda.filmbook.service;

import com.sda.filmbook.model.*;
import com.sda.filmbook.service.exception.CopyNotFoundException;
import com.sda.filmbook.service.exception.CustomerDataNotValidException;
import com.sda.filmbook.service.exception.MovieAlreadyExistsInCatalogueException;
import com.sda.filmbook.service.exception.MovieNotFoundInCatalogueException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class OrderServiceTest {

    @Autowired
    OrderService orderService;
    @Autowired
    MovieService movieService;

    @Autowired
    CustomerService customerService;

    @Autowired
    SessionCartService sessionCartService;

    Map<Movie, Integer> cartMap = new HashMap<>();

    @Test
    public void shouldCreateMovieWithCopiesAndCreateOrderWithCopies() throws MovieAlreadyExistsInCatalogueException, MovieNotFoundInCatalogueException, CustomerDataNotValidException, CopyNotFoundException {
        // given
        Movie movie = new Movie();
        movie.setTitle("AAA");
        movie.setGenre(Genre.HORROR);
        movie.setDescription("AAAAAA");

        Copy copy1 = new Copy();
        copy1.setMovie(movie);

        Copy copy2 = new Copy();
        copy2.setMovie(movie);

        movie.getCopies().add(copy1);
        movie.getCopies().add(copy2);

        Customer customer = new Customer();
        customer.setPhoneNumber("121111111");
        customer.setName("aaaaa");
        customer.setSurname("bbbbbb");
        customer.setEmail("aaaa@aaa.com");
        customer.setAdress("sadsasas");

        Customer newCustomer = customerService.createNewCustomer(customer);


        movieService.addMovieToCatalogue(movie);

        sessionCartService.addMovieToCart(movie);

        Order order = orderService.addCopiesToOrder(newCustomer.getCustomerId());


        Assertions.assertThat(order.getCopies()).isNotEmpty();
        Assertions.assertThat(order.getCopies().size()).isEqualTo(1);


    }

}