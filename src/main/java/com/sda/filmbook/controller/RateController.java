package com.sda.filmbook.controller;

import com.sda.filmbook.model.Rate;
import com.sda.filmbook.service.RateService;
import com.sda.filmbook.service.exception.MovieNotFoundInCatalogueException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RateController {

    private RateService rateService;

    @PostMapping("/rate")
    public ResponseEntity<Rate> addNewRateToMovie(@RequestBody Rate rate,
                                                  @RequestParam(value = "movieId", required = true) Long movieId) throws MovieNotFoundInCatalogueException {
        return ResponseEntity.status(HttpStatus.OK).body(rateService.addNewRate(movieId, rate));
    }

}
