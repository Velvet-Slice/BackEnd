package com.velvetslice.pi_velvetslice.controller;

import com.velvetslice.pi_velvetslice.exception.AutenticacaoException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AutenticacaoException.class)
    public ResponseEntity<String> handleAutenticacaoException(AutenticacaoException e) {

        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
