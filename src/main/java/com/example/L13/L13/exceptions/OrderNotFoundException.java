package com.example.L13.L13.exceptions;

import com.example.L13.L13.models.StatusCode;
import net.bytebuddy.implementation.bind.annotation.Super;

public class OrderNotFoundException extends RuntimeException{

    StatusCode statusCode;

    public OrderNotFoundException(StatusCode statusCode){
        super(statusCode.getExceptionMessage());
        this.statusCode = statusCode;
    }
}
