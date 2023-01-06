package com.fis.microservices.service;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.fis.microservices.entity.Book;

@Repository
public interface BookService {

  Book getBook(int bookId);

  Book saveBook(Book book);
  
  List<Book> getAllBooks();
  
  String subscriberOrUnsubscribeBook(int bookId, String action);


}
