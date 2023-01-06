package com.fis.microservices.dto;

import org.springframework.stereotype.Component;


public class SubscriptionResponse {
  
  private String transactionStatus;
  private String message;
  

  public SubscriptionResponse(String transactionStatus, String message) {  
    this.transactionStatus = transactionStatus;
    this.message = message;
  }


  public String getTransactionStatus() {
    return transactionStatus;
  }


  public String getMessage() {
    return message;
  }
  
  

}
