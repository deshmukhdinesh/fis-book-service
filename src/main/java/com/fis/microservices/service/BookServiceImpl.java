package com.fis.microservices.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fis.microservices.entity.Book;
import com.fis.microservices.repository.BookRepository;

@Component
public class BookServiceImpl implements BookService {

  @Autowired
  private BookRepository bookRepository;

  @Override
  public Book getBook(int bookId) {

    return bookRepository.findById(bookId).orElse(null);
  }

  @Override
  public Book saveBook(Book book) {
    
    return bookRepository.save(book);
  }

  @Override
  public List<Book> getAllBooks() {
   
    return bookRepository.findAll();
  }

  @Override
  public  String subscriberOrUnsubscribeBook(int bookId, String action) {
    Book libaryRecord =getBook(bookId);
    if(libaryRecord != null) {
     int availableCopies = libaryRecord.getBookCopiesAvailable();
     boolean isEligibleToSubscribe = availableCopies>0;
     boolean isEligibleToUnSubscribe = availableCopies+1 <= libaryRecord.getTotalCopies();   
    
      if(action.equals("SUBSCRIBE") && isEligibleToSubscribe ) {        
        libaryRecord.setBookCopiesAvailable(availableCopies-1);
      }
      else if(action.equals("UNSUBSCRIBE") && isEligibleToUnSubscribe) {
        libaryRecord.setBookCopiesAvailable(availableCopies+1);
      }
      else {
        return "Fail";
      }     
    }    
    saveBook(libaryRecord);
    return "Success";
    
    
  }
}
