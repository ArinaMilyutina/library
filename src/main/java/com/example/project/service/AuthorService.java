package com.example.project.service;

import com.example.project.dto.AuthorDto;
import com.example.project.entity.Author;
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

    public Author createdAuthor(AuthorDto authorDto) {
        Author author = Author.builder()
                .name(authorDto.getName())
                .surname(authorDto.getSurname())
                .birthDate(authorDto.getBirthDate())
                .build();
        return authorRepository.save(author);

    }

    public List<Author> findAll() throws NotFoundException {
        List<Author> authorList = authorRepository.findAll();
        if (authorList.isEmpty()) {
            throw new NotFoundException("There are no authors.");
        }
        return authorRepository.findAll();
    }

    public Optional<Author> findById(Long authorId) {
        return authorRepository.findById(authorId);
    }

//    private void updateAuthor(Long authorId, Author updateAuthor) {
//        Optional<Author> authorOptional = findById(authorId);
//        if (authorOptional.isPresent()) {
//            Author author = authorOptional.get();
//            author.setName(updateAuthor.getName());
//            author.setSurname(updateAuthor.getSurname());
//            author.setBirthDate(updateAuthor.getBirthDate());
//            author.setBooks(updateAuthor.getBooks());
//            createdAuthor(author);
//        } else {
//            throw new NotFoundException("Book not found with id: " + authorId);
//        }
//    }

    private void deleteAuthor(Long authorId) {
        authorRepository.deleteById(authorId);
    }
}
