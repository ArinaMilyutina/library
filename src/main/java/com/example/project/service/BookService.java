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
                .dateOfPublication(bookDto.getDateOfPublication())
                .author(author)
                .build();
        return bookRepository.save(book);

    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Optional<Book> findBookById(Long bookId) {
        return bookRepository.findById(bookId);
    }


//    public void updateBook(Long bookId, Book updateBook) {
//        Optional<Book> bookOptional = findBookById(bookId);
//        if (bookOptional.isPresent()) {
//            Book book = bookOptional.get();
//            book.setAuthor(updateBook.getAuthor());
//            book.setTitle(updateBook.getTitle());
//            book.setGenre(updateBook.getGenre());
//            book.setDateOfPublication(updateBook.getDateOfPublication());
//            createdBook(book);
//        } else {
//            throw new NotFoundException("Book not found with id: " + bookId);
//        }
//    }

    public void deleteBook(Long bookId) {
        Optional<Book> bookOptional = findBookById(bookId);
        if (bookOptional.isPresent()) {
            bookRepository.deleteById(bookId);
        } else {
            throw new NotFoundException("Book not found with id: " + bookId);
        }
    }

    public List<Book> findByAuthorId(Long authorId) {
        return bookRepository.findByAuthorId(authorId);
    }
}
