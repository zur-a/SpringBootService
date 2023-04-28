package com.QAspring.SpringBootRestService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<AddResponse> addBookImplementation(@RequestBody Library library) {
		//Retrieving book id
		String id = libraryService.idBuilder(library.getIsbn(), library.getAisle());
		
		//Checking if book is already represented in the database
		libraryService.checkBookExists(id);
		
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
	}
}