package com.example.bookstore.exception;
/**
 * This class is for Resource not found exception
 * 
 * Created By Prakash K
 * Created 8th Aug 2024 
 */
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue){

        super(String.format("%s not found with given input data %s : '%s'", resourceName, fieldName, fieldValue));
    }

}
