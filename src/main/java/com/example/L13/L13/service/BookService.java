package com.example.L13.L13.service;

import com.example.L13.L13.exceptions.ConnectionErrorException;
import com.example.L13.L13.models.Author;
import com.example.L13.L13.models.Book;
import com.example.L13.L13.repository.BookRepository;
import com.example.L13.L13.requests.CreateBookRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BookService {


    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorService authorService;

    public Book createBook(CreateBookRequestDTO createBookRequestDTO){
       Book book = createBookRequestDTO.toBook();

        try {
            Optional<Author> existingAuthor = authorService.findByEmail(book.getAuthor());
            if (existingAuthor.isPresent()) {
                book.setAuthor(existingAuthor.get()); //its important step
                log.info("inside existingAuthor.isPresent() {}",existingAuthor.get());
            } else {
                Author author = authorService.saveOrUpdate(book.getAuthor());
                log.info("saving new Author not Present() {}",author.getEmail());
                book.setAuthor(author); // its important step
            }
        } catch (Exception e){
            throw new ConnectionErrorException();
        }

        return saveOrUpdate(book);
    }

    public Book saveOrUpdate(Book book){
        log.info("book service saveOrUpdate {}",book.toString());
        return bookRepository.save(book);
    }

    public List<Book> fetchAllBooks(){
        return bookRepository.findAll();
    }

    public Optional<Book> fetchById(Long id){
        return bookRepository.findById(id);
    }
}
