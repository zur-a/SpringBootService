package com.QAspring.SpringBootRestService.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.QAspring.SpringBootRestService.repository.BookRepository;
import com.QAspring.SpringBootRestService.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {
	
	@Autowired
	BookService bookservice;
	
	@Autowired
	BookController controller;
	
	@Autowired
	private MockMvc mockmvc;
	
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
		when(repository.save(any())).thenReturn(book);
		
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
	
	//@Test
	//public void getBookNotFoundTest() {
		//Mocking dependencies
		//Book book = buildBook();
		//when(repository.findById(book.getId()).get()).thenReturn(book);
		
		//assertEquals();
	//}
	
	//Tests using MockMvc
	
	@Test
	public void addBookControllerTest() throws Exception {
		//Mocking dependencies
		Book book = buildBook();
		when(service.idBuilder(book.getIsbn(), book.getAisle())).thenReturn(book.getIsbn() + book.getAisle());
		when(service.checkBookExists(book.getId())).thenReturn(true);
		when(repository.save(any())).thenReturn(book);
		
		//Converting the java object "book" to json
		ObjectMapper mapper = new ObjectMapper();
		String bookAsJson = mapper.writeValueAsString(book);
		
		//Mocking the service and validating the status and the id returned
		this.mockmvc.perform(post("/addBook").contentType(MediaType.APPLICATION_JSON).content(bookAsJson))
			.andExpect(status().isAccepted())
			.andExpect(jsonPath("$.id").value(book.getId()));
	}
	
	

}
