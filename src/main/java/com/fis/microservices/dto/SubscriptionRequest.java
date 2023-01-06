package com.fis.microservices.dto;

public class SubscriptionRequest {
  
  private int bookId;
  private String action;
  
  
  public int getBookId() {
    return bookId;
  }
  public void setBookId(int bookId) {
    this.bookId = bookId;
  }
  public String getAction() {
    return action;
  }
  public void setAction(String action) {
    this.action = action;
  }
  
  

}
