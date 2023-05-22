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
		Optional<Book> book = repository.findById(id);
		return book.isPresent() ? true : false;
	}
	
	public Book getBookById(String id) {
		return repository.findById(id).get();
	}
}
