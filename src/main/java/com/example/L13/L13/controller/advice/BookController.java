package com.example.L13.L13.controller.advice;

import com.example.L13.L13.models.Book;
import com.example.L13.L13.requests.CreateBookRequestDTO;
import com.example.L13.L13.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping(value = "/books", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Book>> fetchAllBooks(){
        log.info("Request Received for fetching all books  ");
        /**
         * this should create a stack overflow hell
         */
        return new ResponseEntity<>(bookService.fetchAllBooks(), HttpStatus.OK);
    }

    @PostMapping(value = "/book", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> createBook(@Valid @RequestBody CreateBookRequestDTO createBookRequestDTO){
        log.info("inside controller {}",createBookRequestDTO.toBook());
        return new ResponseEntity<>(bookService.createBook(createBookRequestDTO), HttpStatus.CREATED);
    }

}
