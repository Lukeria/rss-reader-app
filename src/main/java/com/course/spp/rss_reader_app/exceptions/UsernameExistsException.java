package com.course.spp.rss_reader_app.exceptions;

public class UsernameExistsException extends RuntimeException{

    public UsernameExistsException(String message) {
        super(message);
    }
}
