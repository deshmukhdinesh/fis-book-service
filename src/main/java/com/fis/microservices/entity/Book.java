 package com.fis.microservices.entity;

import javax.persistence.Entity;
import javax.persistence.Column;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BOOK")
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "book_id")
  private int bookId;

  @Column(name = "book_name")
  private String bookName;

  @Column(name = "author")
  private String bookAuthor;

  @Column(name = "available_copies")
  private int bookCopiesAvailable;

  @Column(name = "total_copies")
  private int bookTotalCopies;

  public int getBookId() {
    return bookId;
  }

  public void setBookId(int bookId) {
    this.bookId = bookId;
  }

  public String getBookName() {
    return bookName;
  }

  public void setBookName(String bookName) {
    this.bookName = bookName;
  }

  public String getBookAuthor() {
    return bookAuthor;
  }

  public void setBookAuthor(String bookAuthor) {
    this.bookAuthor = bookAuthor;
  }

  public int getBookCopiesAvailable() {
    return bookCopiesAvailable;
  }

  public void setBookCopiesAvailable(int bookCopiesAvailable) {
    this.bookCopiesAvailable = bookCopiesAvailable;
  }

  public int getTotalCopies() {
    return bookTotalCopies;
  }

  public void setTotalCopies(int totalCopies) {
    this.bookTotalCopies = totalCopies;
  }

}
