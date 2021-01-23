package com.sda.filmbook.service.exception;

import javax.validation.constraints.NotNull;

public class MovieNotFoundInSessionCart extends Throwable {
    public MovieNotFoundInSessionCart(String title) {
        super(title);
    }
}
