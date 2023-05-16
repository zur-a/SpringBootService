package com.QAspring.SpringBootRestService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.QAspring.SpringBootRestService.controller.AddResponse;
import com.QAspring.SpringBootRestService.controller.Book;
import com.QAspring.SpringBootRestService.controller.BookController;
import com.QAspring.SpringBootRestService.repository.BookRepository;
import com.QAspring.SpringBootRestService.service.BookService;

@SpringBootTest
class SpringBootRestServiceApplicationTests {
	
	@Autowired
	BookService bookservice;
	
	@Autowired
	BookController controller;
	
	@MockBean
	BookRepository repository;
	
	@MockBean
	BookService service;	
	
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
	
	//Checking controller methods from BookController
	
	@Test
	public void addBookTest() {
		Book book = buildBook();
		
		//Mocking dependencies
		when(service.idBuilder(book.getIsbn(), book.getAisle())).thenReturn(book.getIsbn() + book.getAisle());
		when(service.checkBookExists(book.getId())).thenReturn(false);
		
		ResponseEntity<AddResponse> response = controller.addBook(buildBook());
		
		assertEquals(response.getStatusCode(), HttpStatus.CREATED);
	}
	
	@Test
	public void bookAlreadyAddedTest() {
		Book book = buildBook();
		
		//Mocking dependencies
		when(service.idBuilder(book.getIsbn(), book.getAisle())).thenReturn(book.getIsbn() + book.getAisle());
		when(service.checkBookExists(book.getId())).thenReturn(true);
		
		ResponseEntity<AddResponse> response = controller.addBook(buildBook());
		
		assertEquals(response.getStatusCode(), HttpStatus.ACCEPTED);
	}

}
