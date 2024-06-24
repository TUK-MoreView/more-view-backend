package com.example.moreveiw.domain.friend.Controller.ExceptionControll;

public class FailException extends RuntimeException {
    public FailException(String message) {
        super(message);
    }
}