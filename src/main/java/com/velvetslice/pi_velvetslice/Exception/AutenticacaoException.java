package com.velvetslice.pi_velvetslice.Exception;

import org.aspectj.bridge.IMessage;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AutenticacaoException extends RuntimeException{
    public AutenticacaoException(String message){
        super(message);
    }
}
