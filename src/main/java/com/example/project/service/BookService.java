package com.example.project.service;

import com.example.project.dto.AuthorDto;
import com.example.project.dto.BookDto;
import com.example.project.dto.UpdateBookDto;
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
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    private static final String AUTHOR_NOT_FOUND = "Author not found.";
    private static final String BOOK_NOT_FOUND = "Book not found.";


    public Book createdBook(BookDto bookDto) throws NotFoundException {
        Author author = authorRepository.findById(bookDto.getAuthorId()).orElseThrow(() -> new NotFoundException(AUTHOR_NOT_FOUND));
        Book book = Book.builder()
                .title(bookDto.getTitle())
                .genre(bookDto.getGenre())
                .publicationDate(bookDto.getPublicationDate())
                .author(author)
                .build();
        return bookRepository.save(book);

    }

    public List<Book> findAll() throws NotFoundException {
        List<Book> books = bookRepository.findAll();
        if (books.isEmpty()) {
            throw new NotFoundException(BOOK_NOT_FOUND);
        }
        return books;
    }

    public Optional<Book> findBookById(Long bookId) throws NotFoundException {
        return Optional.ofNullable(bookRepository.findById(bookId).orElseThrow(() -> new NotFoundException(BOOK_NOT_FOUND)));
    }


    public Book updateBook(Long bookId, UpdateBookDto bookDto) throws NotFoundException {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new NotFoundException(BOOK_NOT_FOUND));
        AuthorDto authorDto = bookDto.getAuthorDto();
        Author author = book.getAuthor();
        author.setName(authorDto.getName());
        author.setSurname(authorDto.getSurname());
        author.setBirthDate(authorDto.getBirthDate());
        authorRepository.save(author);
        book.setTitle(bookDto.getTitle());
        book.setGenre(bookDto.getGenre());
        book.setPublicationDate(bookDto.getPublicationDate());
        book.setAuthor(author);
        return bookRepository.save(book);
    }


    public void deleteBook(Long bookId) throws NotFoundException {
        if (!bookRepository.existsById(bookId)) {
            throw new NotFoundException(BOOK_NOT_FOUND);
        }
        bookRepository.deleteById(bookId);
    }


    public List<Book> findByAuthorId(Long authorId) throws NotFoundException {
        if (authorRepository.findById(authorId).isEmpty()) {
            throw new NotFoundException(AUTHOR_NOT_FOUND);
        }
        return bookRepository.findByAuthorId(authorId);
    }
}

