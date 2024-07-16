package com.example.project.service;

import com.example.project.entity.Author;
import com.example.project.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;
    private void createdAuthor(Author author){
        authorRepository.save(author);
    }
    private List<Author> findAll(){
        return authorRepository.findAll();
    }
    private Optional<Author> findById(Long authorId){
        return authorRepository.findById(authorId);
    }

}
