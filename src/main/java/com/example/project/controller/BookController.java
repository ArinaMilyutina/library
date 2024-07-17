package com.example.project.controller;

import com.example.project.dto.BookDto;
import com.example.project.entity.Book;
import com.example.project.exception.NotFoundException;
import com.example.project.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
@Validated
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<Book> addBook(@Valid @RequestBody BookDto bookDto) {
        Book newBook = bookService.createdBook(bookDto);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<Book>> finAllBooks() throws NotFoundException {
        List<Book> books = bookService.findAll();
        return ResponseEntity.of(Optional.ofNullable(books));
    }
}
