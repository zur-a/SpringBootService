package com.QAspring.SpringBootRestService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.QAspring.SpringBootRestService.repository.LibraryRepository;

@RestController
public class LibraryController {
	@Autowired
	LibraryRepository repository;
	
	@PostMapping("/addBook")
	public void addBookImplementation(@RequestBody Library library) {
		//Defining logic to create the book id
		library.setId(library.getIsbn() + library.getAisle());
		
		//Add book data to database table
		repository.save(library);
	}
}
