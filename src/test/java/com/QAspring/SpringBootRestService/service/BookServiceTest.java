package com.QAspring.SpringBootRestService.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.QAspring.SpringBootRestService.controller.Book;
import com.QAspring.SpringBootRestService.controller.BookController;

@SpringBootTest
public class BookServiceTest {
	
	@Autowired
	BookService bookservice;
	
	@Autowired
	BookController controller;
	
	public Book buildBook() {
		Book book = new Book();
		book.setTitle("O divã ocidento-oriental");
		book.setAuthor("Goethe");
		book.setIsbn("978-85-7448-307-8");
		book.setAisle(42);
		book.setId("978-85-7448-307-842");
		return book;
	}
	
	@Test
	void contextLoads() {
	}
	
	//BookService methods
	
	@Test
	public void checkBuildId() {
		assertEquals("978-85-4400-000-714", bookservice.idBuilder("978-85-4400-000-7", 14));
	}
	
	@Test
	public void checkBookExists() {
		assertTrue(bookservice.checkBookExists("978-85-4400-000-714"));
	}
	
	@Test
	public void checkBookDoesNotExists() {
		assertFalse(bookservice.checkBookExists("000-00-0000-000-001"));
	}
}
