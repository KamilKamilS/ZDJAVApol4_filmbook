package com.sda.filmbook.service.exception;

public class MovieAlreadyExistsInCatalogueException extends Exception {

    public MovieAlreadyExistsInCatalogueException(String title) {
        super("movie already exists in catalogue:" + title);
    }
}
