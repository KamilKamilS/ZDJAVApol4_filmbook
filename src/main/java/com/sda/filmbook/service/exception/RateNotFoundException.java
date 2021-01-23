package com.sda.filmbook.service.exception;

public class RateNotFoundException extends Throwable {

    public RateNotFoundException() {
        super("Rate not found");
    }
}
