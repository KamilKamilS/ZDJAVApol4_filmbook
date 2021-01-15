package com.sda.filmbook.service.exception;

public class MovieAlreadyExistsInCatalogueException extends Throwable {

    public MovieAlreadyExistsInCatalogueException(String title) {
        super("movie already exists in catalogue:" + title);
    }
}
