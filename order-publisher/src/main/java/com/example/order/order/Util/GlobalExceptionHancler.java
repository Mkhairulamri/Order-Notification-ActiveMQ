package com.example.order.order.Util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.order.order.Model.ExceptionHandle;

@RestControllerAdvice
public class GlobalExceptionHancler {

    // Handle Error Validation
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionHandle> handleExceptionException(MethodArgumentNotValidException err) {
        ExceptionHandle result = new ExceptionHandle();
        Map<String, Object> data = new HashMap<>();
        for (FieldError error : err.getBindingResult().getFieldErrors()) {
            data.put(error.getField(), error.getDefaultMessage());
        }
        result.setStatus(false);
        result.setMessage("Missing Mandatory Field");
        result.setData(data);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An unexpected error occurred: " + ex.getMessage());
    }

}
