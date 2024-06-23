package com.example.moreveiw.domain.friend.Controller.ExceptionControll;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionController {
    // @Validated 어노테이션을 사용한 유효성 검사에서 에러가 발생하면
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String,String>> handlingToInValidException(ConstraintViolationException e) {
        HashMap<String, String> map = new HashMap<>();
        e.getConstraintViolations().forEach(error -> {
            String key = error.getPropertyPath().toString();
            String message = error.getMessage();
            map.put(key, message);
        });
        return ResponseEntity.status(404).body(map);
    }


    // @Valid 어노테이션을 사용한 유효성 검사에서 에러가 발생하면
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handlingToInValidException(BindingResult result) {
        HashMap<String, String> map = new HashMap<>();
        result.getAllErrors().forEach(error -> {
            String key = ((FieldError)error).getField();
            String message = error.getDefaultMessage();
            map.put(key, message);
        });
        return ResponseEntity.status(404).body(map);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, String>> resourceNotFoundException(ResourceNotFoundException e) {
        return exceptionHandler(e);
    }

    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<Map<String, String>> duplicatesException(DuplicateException e) {
        return exceptionHandler(e);
    }

    @ExceptionHandler(ExceededException.class)
    public ResponseEntity<Map<String, String>> exceededException(ExceededException e) {
        return exceptionHandler(e);
    }

    @ExceptionHandler(FailException.class)
    public ResponseEntity<Map<String, String>> failException(FailException e) {
        return exceptionHandler(e);
    }

    @ExceptionHandler(InvalidException.class)
    public ResponseEntity<Map<String, String>> invalidException(InvalidException e) {
        return exceptionHandler(e);
    }

    @ExceptionHandler(TokenException.class)
    public ResponseEntity<Map<String, String>> tokenException(TokenException e) {
        return exceptionHandler(e);
    }


    private ResponseEntity<Map<String, String>> exceptionHandler(Exception e) {
        HashMap<String, String> map = new HashMap<>();
        map.put("message", e.getMessage());
        return ResponseEntity.status(404).body(map);
    }
}
