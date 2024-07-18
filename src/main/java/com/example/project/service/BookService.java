package com.example.project.service;

import com.example.project.dto.AuthorDto;
import com.example.project.dto.BookDto;
import com.example.project.dto.UpdateBookDto;
import com.example.project.entity.Author;
import com.example.project.entity.Book;
import com.example.project.exception.AuthorNotFoundException;
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


    public Book updateBook(Long bookId, UpdateBookDto bookDto) throws NotFoundException, AuthorNotFoundException {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new NotFoundException("The book with this id was not found."));
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
            throw new NotFoundException("Book not found.");
        }
        bookRepository.deleteById(bookId);
    }


        public List<Book> findByAuthorId (Long authorId) throws NotFoundException {
        if(authorRepository.findById(authorId).isEmpty()){
            throw new NotFoundException("Author not found.");
        }
            return bookRepository.findByAuthorId(authorId);
        }
}

