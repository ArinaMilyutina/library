package com.example.project.service;

import com.example.project.dto.AuthorDto;
import com.example.project.dto.UpdateAuthorBookDto;
import com.example.project.dto.UpdateAuthorDto;
import com.example.project.entity.Author;
import com.example.project.entity.Book;
import com.example.project.exception.NotFoundException;
import com.example.project.repository.AuthorRepository;
import com.example.project.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;
    private static final String AUTHOR_NOT_FOUND = "Author not found.";

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
            throw new NotFoundException(AUTHOR_NOT_FOUND);
        }
        return authorRepository.findAll();
    }

    public Optional<Author> findById(Long authorId) throws NotFoundException {
        return Optional.ofNullable(authorRepository.findById(authorId).orElseThrow(() -> new NotFoundException(AUTHOR_NOT_FOUND)));
    }

    public Author updateAuthor(Long authorId, UpdateAuthorDto updateAuthorDto) throws NotFoundException {
        Optional<Author> authorOptional = authorRepository.findById(authorId);
        if (authorOptional.isPresent()) {
            Author author = authorOptional.get();
            author.setName(updateAuthorDto.getName());
            author.setSurname(updateAuthorDto.getSurname());
            author.setBirthDate(updateAuthorDto.getBirthDate());
            author.getBooks().removeAll(author.getBooks());
            if (updateAuthorDto.getBookList() != null) {
                for (UpdateAuthorBookDto bookDto : updateAuthorDto.getBookList()) {
                    Book book = Book.builder()
                            .title(bookDto.getTitle())
                            .genre(bookDto.getGenre())
                            .publicationDate(bookDto.getPublicationDate())
                            .author(author)
                            .build();
                    book = bookRepository.save(book);
                    author.getBooks().add(book);
                }
            }
            author = authorRepository.save(author);
            return author;
        } else {
            throw new NotFoundException(AUTHOR_NOT_FOUND);
        }
    }

    public void deleteAuthor(Long authorId) throws NotFoundException {
        if (!authorRepository.existsById(authorId)) {
            throw new NotFoundException(AUTHOR_NOT_FOUND);
        }
        authorRepository.deleteById(authorId);
    }
}
