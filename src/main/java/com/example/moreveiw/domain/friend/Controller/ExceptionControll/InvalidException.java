package com.example.moreveiw.domain.friend.Controller.ExceptionControll;

public class InvalidException extends RuntimeException {
    public InvalidException(String message) {
        super(message);
    }
}