package com.example.bookstore.model;
/**
 * This class is for Book Entity
 * 
 * Created By Prakash K
 * Created 8th Aug 2024 
 */
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Cascade;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Book extends BaseEntity {
	
	@Id
	private String isbn;
	
	private String title;

	@OneToMany(cascade =  {CascadeType.ALL})
	@Cascade({org.hibernate.annotations.CascadeType.MERGE,
        org.hibernate.annotations.CascadeType.PERSIST,
        org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
	@JoinColumn(name="book_isbn")
	private List<Author> authors = new ArrayList<>();
	
	private int publicationYear;
	
	private double price;
	
	private String genre;

	public Book() {
		super();
	}
	
	public Book(String isbn, String title, int publicationYear, double price, String genre) {
		super();
		this.isbn = isbn;
		this.title = title;
		//this.authors = authors;
		this.publicationYear = publicationYear;
		this.price = price;
		this.genre = genre;
	}

	public Book(String isbn, String title, List<Author> authors, int publicationYear, double price, String genre) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.authors = authors;
		this.publicationYear = publicationYear;
		this.price = price;
		this.genre = genre;
	}

	public void addAuthor(Author author) {
		this.authors.add(author);
	}
	
	public void removeAuthor(Author author) {
		this.authors.remove(author);
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public int getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", title=" + title + ", authors=" + authors + ", publicationYear="
				+ publicationYear + ", price=" + price + ", genre=" + genre + "]";
	}

}
