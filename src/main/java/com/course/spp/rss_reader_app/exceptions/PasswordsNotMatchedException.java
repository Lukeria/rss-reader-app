package com.course.spp.rss_reader_app.exceptions;

public class PasswordsNotMatchedException extends RuntimeException{

    public PasswordsNotMatchedException(String message) {
        super(message);
    }
}
