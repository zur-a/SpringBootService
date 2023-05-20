package com.QAspring.SpringBootRestService.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.QAspring.SpringBootRestService.repository.BookRepository;
import com.QAspring.SpringBootRestService.service.BookService;

@SpringBootTest
class BookControllerTest {
	
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
		//Mocking dependencies
		Book book = buildBook();
		when(service.idBuilder(book.getIsbn(), book.getAisle())).thenReturn(book.getIsbn() + book.getAisle());
		when(service.checkBookExists(book.getId())).thenReturn(false);
		
		ResponseEntity<AddResponse> response = controller.addBook(buildBook());
		
		assertEquals(response.getStatusCode(), HttpStatus.CREATED);
	}
	
	@Test
	public void bookAlreadyAddedStatusTest() {
		//Mocking dependencies
		Book book = buildBook();
		when(service.idBuilder(book.getIsbn(), book.getAisle())).thenReturn(book.getIsbn() + book.getAisle());
		when(service.checkBookExists(book.getId())).thenReturn(true);
		
		ResponseEntity<AddResponse> response = controller.addBook(buildBook());
		
		assertEquals(response.getStatusCode(), HttpStatus.ACCEPTED);
	}
	
	@Test
	public void bookAlreadyAddedResponseTest() {
		//Mocking dependencies
		Book book = buildBook();
		when(service.idBuilder(book.getIsbn(), book.getAisle())).thenReturn(book.getIsbn() + book.getAisle());
		when(service.checkBookExists(book.getId())).thenReturn(true);
		
		ResponseEntity<AddResponse> response = controller.addBook(buildBook());
		AddResponse ad = response.getBody();
		
		assertEquals(book.getId(), ad.getId());
		assertEquals("Book is already represented in the database", ad.getMessage());
	}
	
	@Test
	public void getBookNotFoundTest() {
		//Mocking dependencies
		Book book = buildBook();
		when(repository.findById(book.getId()).get()).thenReturn(book);
	}

}
