package com.example.bookstore.exception;
/**
 * This class is for Book already exists exception
 * 
 * Created By Prakash K
 * Created 8th Aug 2024 
 */
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BookAlreadyExistsException extends RuntimeException {


    public BookAlreadyExistsException(String message){
        super(message);
    }
}
