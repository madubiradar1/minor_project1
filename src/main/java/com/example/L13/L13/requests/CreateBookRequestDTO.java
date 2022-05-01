package com.example.L13.L13.requests;


import com.example.L13.L13.models.Author;
import com.example.L13.L13.models.Book;
import com.example.L13.L13.models.BookStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookRequestDTO {

    @NotBlank
    String title;

    @NotNull
    @Positive
    Double cost;

    @NotBlank
    String isbn;

    @NotBlank
    String authorName;

    @NotBlank
    @Email
    String authorEmail;

    public Book toBook(){

        Author author = Author.builder()
                .name(authorName)
                .email(authorEmail)
                .build();

        return Book.builder()
                .cost(this.cost)
                .title(this.title)
                .isbn(this.isbn)
                .author(author)
                .bookStatus(BookStatus.AVAILABLE)
                .build();
    }
}
