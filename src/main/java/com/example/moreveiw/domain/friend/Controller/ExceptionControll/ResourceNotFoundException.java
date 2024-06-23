package com.example.moreveiw.domain.friend.Controller.ExceptionControll;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
