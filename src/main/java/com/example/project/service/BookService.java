package com.example.project.service;

import com.example.project.dto.BookDto;
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

    public Book createdBook(BookDto bookDto) {
        Author author = authorRepository.findById(bookDto.getAuthorId()).orElseThrow(() -> new RuntimeException("Author not found"));
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
            throw new NotFoundException("There are no books.");
        }
        return books;
    }

    public Optional<Book> findBookById(Long bookId) throws NotFoundException {
        return Optional.ofNullable(bookRepository.findById(bookId).orElseThrow(() -> new NotFoundException("The book with this id was not found.")));
    }


    public Book updateBook(Long bookId, BookDto bookDto) throws NotFoundException {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new NotFoundException("The book with is id was not found."));
        book.setTitle(bookDto.getTitle());
        book.setGenre(bookDto.getGenre());
        book.setPublicationDate(bookDto.getPublicationDate());
        book.setAuthor(bookDto.getAuthor());
        return bookRepository.save(book);
    }

//    public void deleteBook(Long bookId) {
//        Optional<Book> bookOptional = findBookById(bookId);
//        if (bookOptional.isPresent()) {
//            bookRepository.deleteById(bookId);
//        } else {
//            throw new NotFoundException("Book not found with id: " + bookId);
//        }
//    }

    public List<Book> findByAuthorId(Long authorId) {
        return bookRepository.findByAuthorId(authorId);
    }
}
