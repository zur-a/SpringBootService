package com.QAspring.SpringBootRestService.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.QAspring.SpringBootRestService.controller.Library;
import com.QAspring.SpringBootRestService.repository.LibraryRepository;

@Service
public class LibraryService {
	@Autowired
	LibraryRepository repository;
	
	public String idBuilder(String isbn, int aisle) {
		return isbn + aisle;
	}
	
	public boolean checkBookExists(String id) {
		Optional<Library> library = repository.findById(id);
		return library.isPresent() ? true : false;
	}
}
