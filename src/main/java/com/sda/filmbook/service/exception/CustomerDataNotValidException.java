package com.sda.filmbook.service.exception;

public class CustomerDataNotValidException extends Throwable {

    public CustomerDataNotValidException() {
        super("Invalid data or not full data provide");
    }
}
