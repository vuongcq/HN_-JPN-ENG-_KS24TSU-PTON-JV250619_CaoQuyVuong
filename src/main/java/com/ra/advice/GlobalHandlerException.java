package com.ra.advice;

import com.ra.model.dto.ResponseWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e){
        Map<String, String> errors = new HashMap<>();
        e.getFieldErrors().forEach(error -> {
            errors.put(error.getField(),error.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ResponseWrapper.builder()
                        .success(false)
                        .message("Validation failed")
                        .errors(errors)
                        .httpStatus(HttpStatus.BAD_REQUEST.value())
                        .build()
        );
    }

    @ExceptionHandler (IllegalArgumentException.class)
    public  ResponseEntity<?> handerIllegalArgumentException(IllegalArgumentException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ResponseWrapper.builder()
                        .success(false)
                        .message(e.getMessage())
                        .httpStatus(HttpStatus.BAD_REQUEST.value())
                        .build()
        );
    }
}
