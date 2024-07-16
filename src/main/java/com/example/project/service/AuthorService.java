package com.example.project.service;

import com.example.project.entity.Author;
import com.example.project.entity.Book;
import com.example.project.exception.NotFoundException;
import com.example.project.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    private void createdAuthor(Author author) {
        authorRepository.save(author);
    }

    private List<Author> findAll() {
        return authorRepository.findAll();
    }

    private Optional<Author> findById(Long authorId) {
        return authorRepository.findById(authorId);
    }

    private void updateAuthor(Long authorId, Author updateAuthor) {
        Optional<Author> authorOptional = findById(authorId);
        if (authorOptional.isPresent()) {
            Author author = authorOptional.get();
            author.setName(updateAuthor.getName());
            author.setSurname(updateAuthor.getSurname());
            author.setBirthDate(updateAuthor.getBirthDate());
            author.setBooks(updateAuthor.getBooks());
            createdAuthor(author);
        } else {
            throw new NotFoundException("Book not found with id: " + authorId);
        }
    }
}
