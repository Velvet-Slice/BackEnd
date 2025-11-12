package com.velvetslice.pi_velvetslice.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AutenticacaoException extends RuntimeException {
        public AutenticacaoException(String message) {
            super(message);
        }
}

