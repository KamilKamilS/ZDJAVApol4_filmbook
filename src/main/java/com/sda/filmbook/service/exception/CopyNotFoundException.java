package com.sda.filmbook.service.exception;

public class CopyNotFoundException extends Throwable {

    public CopyNotFoundException(String message) {
        super("no free copy of given movie : " + message);
    }
}
