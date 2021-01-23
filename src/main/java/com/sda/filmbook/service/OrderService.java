package com.sda.filmbook.service;

import com.sda.filmbook.model.Copy;
import com.sda.filmbook.model.Customer;
import com.sda.filmbook.model.Movie;
import com.sda.filmbook.model.Order;
import com.sda.filmbook.repository.CopyRepository;
import com.sda.filmbook.repository.CustomerRepository;
import com.sda.filmbook.repository.OrderRepository;
import com.sda.filmbook.service.exception.CopyNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    CustomerRepository customerRepository;
    OrderRepository orderRepository;
    MovieService movieService;
    SessionCartService sessionCart;

    public Order addCopiesToOrderForCustomer(Long customerId) throws CopyNotFoundException {
        Optional<Customer> customer = customerRepository.findById(customerId);
        Map<Movie, Integer> moviesCopyMap = this.sessionCart.getSessionCart();
        List<Copy> copies = new ArrayList<>();

        for (Map.Entry<Movie, Integer> entry : moviesCopyMap.entrySet()) {
            int numberOfCopy = entry.getValue();
            Movie movie = entry.getKey();
            for (int i = 0; i < numberOfCopy; i++) {
                Copy copy = movieService.getFreeCopyOfMovie(movie);
                copies.add(copy);
            }
        }
        Order order = createOrder();
        order.setCopies(copies);

        return orderRepository.saveAndFlush(order);
    }

    private Order createOrder() {
        return new Order();
    }

    public Order addReturnDate(LocalDate date, Long orderId) {
        Order order = orderRepository.findById(orderId).get();
        order.setReturnDate(date);
        return orderRepository.saveAndFlush(order);
    }

    public Order addOrderedDate(LocalDate date, Long orderId) {
        Order order = orderRepository.findById(orderId).get();
        order.setOrderedDate(date);
        return orderRepository.saveAndFlush(order);
    }

    public Order finalizeOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).get();
        order.setOrderedDate(LocalDate.now());
        return orderRepository.saveAndFlush(order);
    }

    //TODO - calculate price
}
