package com.QAspring.SpringBootRestService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.QAspring.SpringBootRestService.repository.LibraryRepository;
import com.QAspring.SpringBootRestService.service.LibraryService;

@RestController
public class LibraryController {
	
	@Autowired
	LibraryRepository repository;
	
	@Autowired
	AddResponse response;
	
	@Autowired
	LibraryService libraryService;
	
	@PostMapping("/addBook")
	public ResponseEntity<AddResponse> addBook(@RequestBody Library library) {
		//Retrieving book id
		String id = libraryService.idBuilder(library.getIsbn(), library.getAisle());
		
		//Checking if book is already represented in the database
		if(!libraryService.checkBookExists(id)) {
			//Sets the id for the book
			library.setId(id);
			
			//Add book data to database table
			repository.save(library);
			
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
			
			//Adding message to the response
			response.setMessage("Book is already represented in the database");
			response.setId(id);
			
			//Returns response in json format
			return new ResponseEntity<AddResponse>(response, HttpStatus.ACCEPTED);
		}
	}
	
	@GetMapping("/getBook/{id}")
	public Library getBookById(@PathVariable(value="id") String id) {
		try {
			Library library = repository.findById(id).get();
			return library;
		} catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/getBook/author")
	public List<Library> getBookByAuthor(@RequestParam(value="authorName")String authorName) {
		return repository.findAllByAuthor(authorName, repository);
	}
	
	@PutMapping("/updateBook/{id}")
	public void bookUpdate(@PathVariable(value="id") String id) {
		
	}
	
}