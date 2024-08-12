package com.example.bookstore.service;
/**
 * This class is for Book Store Service interface
 * 
 * Created By Prakash K
 * Created 8th Aug 2024 
 */
import java.util.List;

import com.example.bookstore.model.Book;

public interface IBookStoreService {
	
	void addBook(Book book);
	
	boolean updateBook(Book book);
	
	Book fetchBookByTitleAndAuthorName(String title, String name);

	List<Book> fetchBookByTitleOrAuthorName(String title, String name);
	
	boolean deleteBook(String isbn);

}
