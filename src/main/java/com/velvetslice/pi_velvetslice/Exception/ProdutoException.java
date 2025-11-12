package com.velvetslice.pi_velvetslice.Exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;

//teste
@RestControllerAdvice
public class ProdutoException extends RuntimeException{

    public ProdutoException (String message){
        super(message);
    }
}