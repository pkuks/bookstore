package com.example.bookstore.repository;
/**
 * This class is for Author Repository
 * 
 * Created By Prakash K
 * Created 8th Aug 2024 
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookstore.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>{

}
