package com.sda.filmbook.service.exception;

public class MovieNotFoundInCatalogueException extends Throwable {

    public MovieNotFoundInCatalogueException(String title) {
        super(title + " movie not found in catalogue");
    }
}
