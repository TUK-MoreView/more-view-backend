package com.example.moreveiw.domain.friend.Controller.ExceptionControll;

public class ExceededException extends RuntimeException {
    public ExceededException(String message) {
        super(message);
    }
}