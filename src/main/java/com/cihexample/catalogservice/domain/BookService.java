package com.cihexample.catalogservice.domain;

import com.cihexample.catalogservice.common.exception.BookAlreadyExistsException;
import com.cihexample.catalogservice.common.exception.BookNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Iterable<Book> viewBookList(){
        return bookRepository.findAll();
    }

    public Book viewBookDetails(String isbn) {
        return bookRepository.findByIsbn(isbn).orElseThrow(() -> new BookNotFoundException(isbn));
    }

    public Book addBookToCatalog(Book book) {
        if(bookRepository.existsIsbn(book.isbn())){
            throw new BookAlreadyExistsException(book.isbn());
        }
        return bookRepository.save(book);
    }

    public void removeBookFromCatalog(String isbn) {
        bookRepository.deleteByIsBn(isbn);
    }

    public Book editBookDetails(String isbn, Book book) {
        return bookRepository.findByIsbn(isbn).map(existingBook ->{
            var bookToUpdate = new Book(
                    existingBook.id(),
                    existingBook.isbn(),
                    book.title(),
                    book.author(),
                    book.price(),
                    existingBook.createdDate(),
                    existingBook.lastModifiedDate(),
                    existingBook.version()
            );
            return bookRepository.save(bookToUpdate);
        }).orElseGet(()->addBookToCatalog(book));
    }

}
