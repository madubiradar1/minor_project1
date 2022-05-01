package com.example.L13.L13.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@Table(name = "my_book")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String title;

    String isbn;

    Double cost;

    @Enumerated(value = EnumType.STRING)
    BookStatus bookStatus;
    /*
     * by-default it joins with primary key of another table
     */

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Author author;

    @ManyToOne
    @JoinColumn
    @ToString.Exclude
    @JsonIgnoreProperties(value = "bookList")
    private UserInfo user;

    @OneToMany(mappedBy = "associatedBook")
    @JsonIgnore
    @ToString.Exclude
    private Orders ordersList;

    @CreationTimestamp
    LocalDateTime creationDate;

    @UpdateTimestamp
    LocalDateTime updatedDate;
}
