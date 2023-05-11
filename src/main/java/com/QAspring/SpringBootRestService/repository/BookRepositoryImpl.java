package com.QAspring.SpringBootRestService.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.QAspring.SpringBootRestService.controller.Book;

public class BookRepositoryImpl implements BookRepositoryCustom {
	
	//@Autowired
	//LibraryRepository repository;
	
	@Override
	public List<Book> findAllByAuthor(String authorName, BookRepository repository) {
		List<Book> books = repository.findAll();
		List<Book> authorBooks = new ArrayList<Book>();
		for (Book book : books) {
			if (book.getAuthor().equalsIgnoreCase(authorName)) {
				authorBooks.add(book);
			}
		}
		return authorBooks;
	}

	@Override
	public Book findByTitle(String title, BookRepository repository) {
	    List<Book> books = repository.findAll();
	    for (Book book : books) {
	        if (book.getTitle().equalsIgnoreCase(title)) {
	            return book;
	        }
	    }
	    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
	}
}
