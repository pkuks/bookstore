package com.example.bookstore.repository;
/**
 * This class is for Book Repository
 * 
 * Created By Prakash K
 * Created 8th Aug 2024 
 */
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.bookstore.model.Book;

import jakarta.transaction.Transactional;

@Repository
public interface BookRepository extends JpaRepository<Book, String>{
    Optional<Book> findByIsbn(String isbn);
    
    @Transactional
    @Modifying
    void deleteByIsbn(String isbn);
    
    @Query(value="select b.* from book b inner join author a on a.book_isbn=b.isbn where b.title=?1 and a.name=?2",
    		nativeQuery=true)
    Optional<Book> findByTitleAndAuthorName(String title, String name);
    
    @Query(value="select b.* from book b inner join author a on a.book_isbn=b.isbn where b.title=?1 or a.name=?2",
    		nativeQuery=true)
    List<Book> findByTitleOrAuthorName(String title, String name);
  

}
