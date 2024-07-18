package com.example.project.controller;

import com.example.project.dto.BookDto;
import com.example.project.dto.UpdateBookDto;
import com.example.project.entity.Book;
import com.example.project.exception.AuthorNotFoundException;
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

    @GetMapping("/{id}")
    public ResponseEntity<Book> findBookById(@PathVariable Long id) throws NotFoundException {
        Optional<Book> book = bookService.findBookById(id);
        return ResponseEntity.of(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @Valid @RequestBody UpdateBookDto bookDto) throws NotFoundException, AuthorNotFoundException {
        Book updateBook = bookService.updateBook(id, bookDto);
        return ResponseEntity.ok(updateBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) throws NotFoundException {
        bookService.deleteBook(id);
        return new ResponseEntity<>("Book deleted successfully", HttpStatus.OK);

    }
    @GetMapping("/author/{id}")
    public ResponseEntity<List<Book>>findBookByIdAuthor(@PathVariable Long id) {
        List<Book>books=bookService.findByAuthorId(id);
        return new ResponseEntity<>(books,HttpStatus.OK);
    }
}