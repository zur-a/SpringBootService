package com.QAspring.SpringBootRestService.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.QAspring.SpringBootRestService.controller.Book;
import com.QAspring.SpringBootRestService.repository.BookRepository;

@Service
public class BookService {
	@Autowired
	BookRepository repository;
	
	public String idBuilder(String isbn, int aisle) {
		return isbn + aisle;
	}
	
	public boolean checkBookExists(String id) {
		Optional<Book> library = repository.findById(id);
		return library.isPresent() ? true : false;
	}
}
