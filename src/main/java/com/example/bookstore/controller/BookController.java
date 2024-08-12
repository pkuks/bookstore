package com.example.bookstore.controller;

/**
 * This class provides the APIs for BookStore
 * Created By Prakash K
 * Created 8th Aug 2024
 * 
 */

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookstore.constants.BookStoreConstants;
import com.example.bookstore.dto.ResponseDto;
import com.example.bookstore.model.Book;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.service.impl.BookStoreServiceImpl;

import jakarta.annotation.security.RolesAllowed;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookController {

	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	BookStoreServiceImpl bookstoreService;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

		
	/**
	 * This method allows user to add a book
	 * @param book
	 * @return
	 */
	@PostMapping(path = "/addBook")
	public ResponseEntity<ResponseDto> addBook(@RequestBody Book book) {
		//Book book = new Book();
		logger.info("adding book " + book.toString());
		bookstoreService.addBook(book);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(new ResponseDto(BookStoreConstants.STATUS_201, BookStoreConstants.MESSAGE_201));
	}
	
	/**
	 * This method allows user to fetch a book by title and author name
	 * @param title
	 * @param name
	 * @return
	 */
	@GetMapping(path = "/fetchBookByTitleAndAuthorName")
	public ResponseEntity<Book> fetchBookByTitleAndAuthorName(@RequestParam String title,
			@RequestParam String name){
		Book book = bookstoreService.fetchBookByTitleAndAuthorName(title, name);
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(book);
	}

	/**
	 * This method allows user to fetch a book by title or author name
	 * @param title
	 * @param name
	 * @return
	 */
	@GetMapping(path = "/fetchBookByTitleOrAuthorName")
	public ResponseEntity<List<Book>> fetchBookByTitleOrAuthorName(@RequestParam String title,
			@RequestParam String name){
		List<Book> books = bookstoreService.fetchBookByTitleOrAuthorName(title, name);
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(books);
	}
	
	/**
	 * This method allows user to update the book
	 * @param book
	 * @return
	 */
	@PutMapping("/updateBook")
	public ResponseEntity<ResponseDto> updateBook(@RequestBody Book book){
		boolean isUpdated = bookstoreService.updateBook(book);
		if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(BookStoreConstants.STATUS_200, BookStoreConstants.MESSAGE_200));
        }
        else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(BookStoreConstants.STATUS_417, BookStoreConstants.MESSAGE_417_UPDATE));
        }
	}
	
	/**
	 * This method allows user to delete the book by ISBN
	 * This method is accessible only by ROLE ADMIN
	 * @param isbn
	 * @return
	 */
	@DeleteMapping("/deleteBook")
	@RolesAllowed({"ADMIN"})
	//@Secured({"SCOPE_ROLE_ADMIN"})
	public ResponseEntity<ResponseDto> deleteBook(@RequestParam String isbn){
		boolean isDeleted = bookstoreService.deleteBook(isbn);
		if (isDeleted) {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(new ResponseDto(BookStoreConstants.STATUS_200, BookStoreConstants.MESSAGE_200));
		}
		else {
			return ResponseEntity
					.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseDto(BookStoreConstants.STATUS_417, BookStoreConstants.MESSAGE_417_DELETE));
		}
	}

}
