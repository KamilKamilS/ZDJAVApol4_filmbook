package com.sda.filmbook.service.exception;

public class MovieNotFoundInCatalogue extends Throwable {

    public MovieNotFoundInCatalogue(String title) {
        super(title + "movie not found in catalogue");
    }
}
