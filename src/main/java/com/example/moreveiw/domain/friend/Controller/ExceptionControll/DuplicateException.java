package com.example.moreveiw.domain.friend.Controller.ExceptionControll;

public class DuplicateException extends RuntimeException {
    public DuplicateException(String message) {
        super(message);
    }
}
