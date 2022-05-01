package com.example.L13.L13.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@Getter
public enum StatusCode {

    CHEGG_01("BNF","Book NOT FOUND", HttpStatus.BAD_REQUEST),
    CHEGG_02("ONF","Order NOT FOUND",HttpStatus.BAD_REQUEST);

    String statusCode;

    String ExceptionMessage;

    HttpStatus httpStatus;


    StatusCode(String statusCode, String ExceptionMessage, HttpStatus httpStatus){
        this.statusCode = statusCode;
        this.ExceptionMessage = ExceptionMessage;
        this.httpStatus = httpStatus;
    }

}
