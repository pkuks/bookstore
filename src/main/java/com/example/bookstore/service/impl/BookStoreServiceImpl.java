package com.example.bookstore.service.impl;
/**
 * This class implements Book Store Service interface
 * 
 * Created By Prakash K
 * Created 8th Aug 2024 
 */
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bookstore.exception.BookAlreadyExistsException;
import com.example.bookstore.exception.ResourceNotFoundException;
import com.example.bookstore.model.Author;
import com.example.bookstore.model.Book;
import com.example.bookstore.repository.AuthorRepository;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.service.IBookStoreService;

@Service
public class BookStoreServiceImpl implements IBookStoreService{

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private AuthorRepository authorRepository;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void addBook(Book book) {
		Optional<Book> optionalBook = bookRepository.findByIsbn(book.getIsbn());
		if (optionalBook.isPresent()) {
			throw new BookAlreadyExistsException("Book already exists with given ISBN " + book.getIsbn());
		}
		book.setCreatedAt(LocalDateTime.now());
		book.setCreatedBy("user1");
		List<Author> authors = book.getAuthors();
		logger.info("book " + book.toString());
		for (Author author: authors) {
			//author.setBook(savedBook);
			author.setCreatedAt(LocalDateTime.now());
			author.setCreatedBy("user1");
		}
		Book savedBook = bookRepository.save(book);
		logger.info("authors " + authors.toString());
	}

	@Override
	public Book fetchBookByTitleAndAuthorName(String title, String name) {
		logger.info("BookStoreServiceImpl - fetchBookByTitleAndAuthorName - title :" + title + ", name :" + name);
		Book book = bookRepository.findByTitleAndAuthorName(title, name).orElseThrow(
				 ()-> new ResourceNotFoundException("Book", "title - name ", title + " - " + name));
		if (book!=null) {
			logger.info("Book " + book.toString());
		}
		return book;
	}

	@Override
	public List<Book> fetchBookByTitleOrAuthorName(String title, String name) {
		logger.info("BookStoreServiceImpl - fetchBookByTitleAndAuthorName - title :" + title + ", name :" + name);
		List<Book> books = bookRepository.findByTitleOrAuthorName(title, name);
		//logger.info("Book " + book.toString());
		if (books.isEmpty()) {
			throw new ResourceNotFoundException("Book", "title - name ", title + " - " + name);
		}
		return books;
	}
	
	
	@Override
	public boolean updateBook(Book book) {
		boolean isUpdated = false;
		Optional<Book> optionalBook = bookRepository.findByIsbn(book.getIsbn());
		if (optionalBook.isPresent()) {
			book.setUpdatedAt(LocalDateTime.now());
			book.setUpdatedBy("user1");
			List<Author> authors = book.getAuthors();
			for (Author author: authors) {
				author.setCreatedAt(LocalDateTime.now());
				author.setCreatedBy("user1");
				author.setUpdatedAt(LocalDateTime.now());
				author.setUpdatedBy("user1");
			}
			bookRepository.save(book);
			isUpdated=true;
		}
		else {
			throw new ResourceNotFoundException("Book", "isbn", book.getIsbn());
		}
		return isUpdated;
	}

	@Override
	public boolean deleteBook(String isbn) {
		boolean isDeleted = false;
		Optional<Book> optionalBook = bookRepository.findByIsbn(isbn);
		if (optionalBook.isPresent()) {
			bookRepository.deleteByIsbn(isbn);
			isDeleted=true;
		}
		else {
			throw new ResourceNotFoundException("Book", "isbn", isbn);
		}
		return isDeleted;
	}

}
