package com.example.project.service;

import com.example.project.entity.Book;
import com.example.project.exception.NotFoundException;
import com.example.project.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    private void createdBook(Book book) {
        bookRepository.save(book);
    }

    private List<Book> findAll() {
        return bookRepository.findAll();
    }

    private Optional<Book> findBookById(Long bookId) {
        return bookRepository.findById(bookId);
    }


    private void updateBook(Long bookId, Book updateBook) {
        Optional<Book> bookOptional = findBookById(bookId);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            book.setAuthor(updateBook.getAuthor());
            book.setTitle(updateBook.getTitle());
            book.setGenre(updateBook.getGenre());
            book.setDateOfPublication(updateBook.getDateOfPublication());
            createdBook(book);
        } else {
            throw new NotFoundException("Book not found with id: " + bookId);
        }
    }
}
