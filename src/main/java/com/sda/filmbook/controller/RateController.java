package com.sda.filmbook.controller;

import com.sda.filmbook.model.Rate;
import com.sda.filmbook.service.RateService;
import com.sda.filmbook.service.exception.MovieNotFoundInCatalogueException;
import com.sda.filmbook.service.exception.RateNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RateController {

    private RateService rateService;

    @PostMapping("/rate")
    public ResponseEntity<Rate> addNewRateToMovie(@RequestBody Rate rate,
                                                  @RequestParam(value = "movieId", required = true) Long movieId) throws MovieNotFoundInCatalogueException {
        return ResponseEntity.status(HttpStatus.OK).body(rateService.addNewRate(movieId, rate));
    }

    @GetMapping("/rate/{id}")
    public ResponseEntity<Rate> getRateById(@PathVariable Long id) throws RateNotFoundException {
        return rateService.getRateById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity
                .notFound()
                .build());
    }

    @GetMapping("/rate")
    public ResponseEntity<List<Rate>> getAllRatesOfMovie(@RequestParam Long movieId) throws MovieNotFoundInCatalogueException {
        List<Rate> allRatesOfMovie = rateService.getAllRatesOfMovie(movieId);
        if (allRatesOfMovie != null) {
            return ResponseEntity.status(HttpStatus.OK).body(allRatesOfMovie);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

}
