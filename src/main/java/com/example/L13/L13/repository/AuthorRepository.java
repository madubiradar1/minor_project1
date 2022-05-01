package com.example.L13.L13.repository;

import com.example.L13.L13.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {

    public Optional<Author> findByEmail(String email);
}
