package com.example.bookstore.model;

/**
 * This class is for Author Entity
 * 
 * Created By Prakash K
 * Created 8th Aug 2024 
 */
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
public class Author extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	
	private String name;
	
	private LocalDate dob;
	
	public Author() {
		super();
	}

	public Author(Long id, String name, LocalDate dob, Book book) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;
		//this.book = book;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	@Override
	public String toString() {
		return "Author [id=" + id + ", name=" + name + ", dob=" + dob + "]";
	}
	
	
	
		
}
