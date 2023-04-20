package com.QAspring.SpringBootRestService.controller;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="Library")
public class Library {
	@Column(name="id")
	private String id;
	@Column(name="book_name")
	private String book_name;
	@Column(name="isbn")
	private String isbn;
	@Column(name="aisle")
	private int aisle;
	@Column(name="author")
	private String author;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getBook_name() {
		return book_name;
	}
	
	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}
	
	public String getIsbn() {
		return isbn;
	}
	
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public int getAisle() {
		return aisle;
	}
	
	public void setAisle(int aisle) {
		this.aisle = aisle;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
}
