//package com.stackroute.exceptions;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//
//@ControllerAdvice
//public class GlobalException {
//    //ExceptionHandler for TrackAlreadyExistsException//
//    @ExceptionHandler(TrackAlreadyExistsException.class)
//    public ResponseEntity handleTrackAlreadyExistsException(Exception ex){
//        return new ResponseEntity(ex.getMessage(), HttpStatus.CONFLICT);
//    }
//    //ExceptionHandler for TrackNotFoundException//
//    @ExceptionHandler(TrackNotFoundException.class)
//    public ResponseEntity handleTrackNotFoundException( Exception ex){
//        return new ResponseEntity(ex.getMessage(), HttpStatus.CONFLICT);
//    }
//}
