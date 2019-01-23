package com.stackroute.exceptions;

public class TrackAlreadyExistsException extends Exception {
    private String message;
    //TrackAlreadyExistsException method//
    public TrackAlreadyExistsException() {
    }
    public TrackAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }
}