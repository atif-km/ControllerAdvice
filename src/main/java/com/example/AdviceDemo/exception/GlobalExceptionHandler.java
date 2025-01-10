package com.example.AdviceDemo.exception;

import com.example.AdviceDemo.bean.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleAdviceException(){
        ErrorResponse errorResponse = new ErrorResponse() 
    }
}
