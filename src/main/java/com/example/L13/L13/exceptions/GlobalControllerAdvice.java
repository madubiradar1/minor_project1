package com.example.L13.L13.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.xml.ws.Response;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<String> generateBadRequestResponse(){
        return new ResponseEntity<String>("BOOK NOT FOUND BAD REQUEST", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConnectionErrorException.class)
    public ResponseEntity<String> generateConnectionError(){
        return new ResponseEntity<String>("Connection to DB lost", HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<String> generateOrderException(){
        return new ResponseEntity<String>("OrderNotFound", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> generateUserException(){
        return  new ResponseEntity<String>("User Not Found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookNotAvailableException.class)
    public ResponseEntity<String> generateBookNotAvailableException(){
        return  new ResponseEntity<String>("Book not available", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookQuotaExceeded.class)
    public ResponseEntity<String> generateBookQuotaException(){
        return new ResponseEntity("Book Quota Exceeded ", HttpStatus.NOT_ACCEPTABLE);
    }
}
