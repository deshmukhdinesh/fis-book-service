package com.fis.microservices.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.fis.microservices.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

  Optional<Book> findById(Integer id);
  
  
  @Modifying
  @Query("update Book b set b.bookCopiesAvailable = :actionValue  where b.bookId = :bookId")
  Book updateLiabrary(@Param(value = "bookId") int bookId, @Param(value = "actionValue") int actionValue);
  
 
  
  

}
