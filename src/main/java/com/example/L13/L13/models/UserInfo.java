package com.example.L13.L13.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@ToString
@Getter
@Setter
@Builder
@Entity
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;


    String name;

    String email;

    String address;

    Long phoneNumber;

    @OneToMany(mappedBy="user")
    @ToString.Exclude
    @JsonIgnore
    private List<Book> issuedBooks;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    @JsonIgnore
    private List<Orders> ordersList;

    @CreationTimestamp
    LocalDateTime creationDate;

    @UpdateTimestamp
    LocalDateTime updatedDate;



}
