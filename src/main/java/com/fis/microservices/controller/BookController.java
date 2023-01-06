package com.fis.microservices.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fis.microservices.dto.SubscriptionRequest;
import com.fis.microservices.dto.SubscriptionResponse;
import com.fis.microservices.entity.Book;
import com.fis.microservices.service.BookService;

@RestController
@RequestMapping("books")
public class BookController {

  @Autowired
  private BookService bookService;
  
  private final String BOOK_ACTION_PERFORMED = " Your Request completed successfully";
  private final String BOOK_ACTION_NOT_PERFORMED = " Your Request can't performed please try after sometime";

  @GetMapping("")
  public  HttpEntity<List<Book>> getAllBook() {
    List<Book> listOfBooks = null;
    HttpStatus requestedBookStatus = HttpStatus.OK;
    try {
      listOfBooks =bookService.getAllBooks();
      if(listOfBooks==null) {
        requestedBookStatus=HttpStatus.BAD_REQUEST;
      }
    } catch (Exception e) {
      requestedBookStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }  

    return new ResponseEntity<>(listOfBooks, requestedBookStatus);
   
  }
  
  @GetMapping("/{bookId}")
  public  HttpEntity<Book> getBook(@PathVariable("bookId") int bookId) {

    Book requestedBook = null;
    HttpStatus requestedBookStatus = HttpStatus.ACCEPTED;
    try {
      requestedBook =bookService.getBook(bookId);
      if(requestedBook==null) {
        requestedBookStatus=HttpStatus.BAD_REQUEST;
      }
    } catch (Exception e) {
      requestedBookStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }   

    return new ResponseEntity<>(requestedBook, requestedBookStatus);
   
  }

  @PostMapping("/addBook")
  public ResponseEntity<Book> addBook(@RequestBody Book book) {
    Book saveBook = null;
    HttpStatus saveBookStatus = HttpStatus.CREATED;
    try {
      saveBook = bookService.saveBook(book);
    } catch (Exception e) {
      saveBookStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    return new ResponseEntity<Book>(saveBook, saveBookStatus);
  }
  
  @PostMapping("/updateLibrary")
  public ResponseEntity<SubscriptionResponse> updateLibrary(@RequestBody SubscriptionRequest subscriptionRequest ) {
    String responseMessage = null;
    String transactionStatus = "Fail";
    HttpStatus bookRecordUpdateStatus = HttpStatus.ACCEPTED;
    try {
      transactionStatus= bookService.subscriberOrUnsubscribeBook(subscriptionRequest.getBookId(), subscriptionRequest.getAction());
      responseMessage = transactionStatus=="Success"?BOOK_ACTION_PERFORMED:BOOK_ACTION_NOT_PERFORMED;
    } catch (Exception e) {
      transactionStatus = "Fail";
      responseMessage= BOOK_ACTION_NOT_PERFORMED;
      bookRecordUpdateStatus = HttpStatus.INTERNAL_SERVER_ERROR;    }
    

    return new ResponseEntity<SubscriptionResponse>(new SubscriptionResponse(transactionStatus, responseMessage), bookRecordUpdateStatus);
   
  }



}
