package com.codingdojo.authentication.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.authentication.models.Book;
import com.codingdojo.authentication.models.User;
import com.codingdojo.authentication.repositories.BookRepository;

@Service
public class BookService {
	@Autowired
    private BookRepository bookRepository;
	
	 // returns all the books
    public List<Book> allBooks() {
        return (List<Book>) bookRepository.findAll();
    }
    
    
    // creates a book
    public Book createBook(Book b) {
        return bookRepository.save(b);
    }
    
    
    // retrieves a book
    public Book findBook(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(optionalBook.isPresent()) {
            return optionalBook.get();
        } else {
            return null;
        }
    }
    
    
    //update a Book 
    public Book updateBook(Book b) {
        return bookRepository.save(b);
    }
    
    
    
    // delete a book
    public void deleteBook(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(optionalBook.isPresent()) {
        	bookRepository.deleteById(id);
        } 
    }

}
