package com.stackroute.exceptions;

public class TrackNotFoundException extends Exception{
    private String message;
    //TrackNotFoundException() method//
    public TrackNotFoundException() {
    }
    public TrackNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}




