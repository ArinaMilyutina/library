package com.example.project.controller;

import com.example.project.dto.AuthorDto;
import com.example.project.dto.UpdateAuthorDto;
import com.example.project.entity.Author;
import com.example.project.exception.NotFoundException;
import com.example.project.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/authors")
@Validated
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @PostMapping
    public ResponseEntity<Author> addAuthor(@Valid @RequestBody AuthorDto authorDto) {
        Author newAuthor = authorService.createdAuthor(authorDto);
        return new ResponseEntity<>(newAuthor, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<Author>> findAllAuthors() throws NotFoundException {
        List<Author> authors = authorService.findAll();
        return ResponseEntity.of(Optional.ofNullable(authors));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> findAuthorById(@PathVariable Long id) throws NotFoundException {
        Optional<Author> author = authorService.findById(id);
        return ResponseEntity.of(author);
    }

    @PutMapping("/{authorId}")  // Используйте @PutMapping для метода обновления
    public ResponseEntity<Author> updateAuthor(@PathVariable Long authorId, @Valid @RequestBody UpdateAuthorDto authorDto) throws NotFoundException {
        Author updatedAuthor = authorService.updateAuthor(authorId, authorDto);
        return new ResponseEntity<>(updatedAuthor, HttpStatus.OK);
    }

}
