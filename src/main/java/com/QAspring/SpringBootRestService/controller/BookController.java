package com.QAspring.SpringBootRestService.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.QAspring.SpringBootRestService.repository.BookRepository;
import com.QAspring.SpringBootRestService.service.BookService;

@RestController
public class BookController {
	
	@Autowired
	BookRepository repository;
	
	@Autowired
	AddResponse response;
	
	@Autowired
	BookService service;
	
	//Keeps track of log information
	private static final Logger logger = LoggerFactory.getLogger(BookController.class);
	
	@PostMapping("/addBook")
	public ResponseEntity<AddResponse> addBook(@RequestBody Book book) {
		//Retrieving book id
		String id = service.idBuilder(book.getIsbn(), book.getAisle());
		
		//Checking if book is already represented in the database
		if(!service.checkBookExists(id)) {
			//Registering in the log
			logger.info("Creating a new book in the database");
			
			//Sets the id for the book
			book.setId(id);
			
			//Add book data to database table
			repository.save(book);
			
			//Add successful message
			response.setMessage("Book is succesfully added");
			response.setId(id);
			
			//Creating headers for the response
			HttpHeaders header = new HttpHeaders();
			header.add("Unique", id);
			
			//Returns response in json format
			return new ResponseEntity<AddResponse>(response, header, HttpStatus.CREATED);
			
		} else {
			//Returns message "Book is already represented in the database" with status code 202
			
			//Registering in the log
			logger.info("Book already exists in the database");
			
			//Adding message to the response
			response.setMessage("Book is already represented in the database");
			response.setId(id);
			
			//Returns response in json format
			return new ResponseEntity<AddResponse>(response, HttpStatus.ACCEPTED);
		}
	}
	
	@GetMapping("/getBook/{id}")
	public Book getBookById(@PathVariable(value="id") String id) {
		try {
			Book book = repository.findById(id).get();
			return book;
		} catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/getBook/author")
	public List<Book> getBookByAuthor(@RequestParam(value="authorName")String authorName) {
		return repository.findAllByAuthor(authorName, repository);
	}
	
	@GetMapping("/getBook/title")
	public Book getBookByTitle(@RequestParam(value="bookTitle")String bookTitle) {
		return repository.findByTitle(bookTitle, repository);
	}
	
	@PutMapping("/updateBook/{id}")
	public ResponseEntity<Book> bookUpdate(@PathVariable(value="id") String id, @RequestBody Book newData) {
		//Finds the book to be updated
		Book bookToBeUpdated = getBookById(id);
		
		//Setting the new values to the existing book
		bookToBeUpdated.setAisle(newData.getAisle());
		bookToBeUpdated.setAuthor(newData.getAuthor());
		bookToBeUpdated.setTitle(newData.getTitle());
		
		//Sending the new details to update the table in the database
		repository.save(bookToBeUpdated);
		
		//Registering in the log
		logger.info("Book updated in the database");
		
		//Returning a Http status code as feedback
		return new ResponseEntity<Book>(bookToBeUpdated, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteBook")
	public ResponseEntity<String> deleteBookById(@RequestBody Book book) {
		try {
			//Finding the book by id
			Book bookToBeDeleted = repository.findById(book.getId()).get();
			
			//Deleting the book from the database
			repository.delete(bookToBeDeleted);
			
			//Registering in the log
			logger.info("Book deleted from the database");
			
			//Returning the feedback if the book is deleted
			return new ResponseEntity<>("The book has been deleted", HttpStatus.CREATED);
		} catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
}