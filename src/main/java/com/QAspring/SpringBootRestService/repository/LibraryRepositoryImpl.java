package com.QAspring.SpringBootRestService.repository;

import java.util.ArrayList;
import java.util.List;
import com.QAspring.SpringBootRestService.controller.Book;

public class LibraryRepositoryImpl implements LibraryRepositoryCustom {
	
	//@Autowired
	//LibraryRepository repository;
	
	@Override
	public List<Book> findAllByAuthor(String authorName, LibraryRepository repository) {
		List<Book> books = repository.findAll();
		List<Book> authorBooks = new ArrayList<Book>();
		for (Book book : books) {
			if (book.getAuthor().equalsIgnoreCase(authorName)) {
				authorBooks.add(book);
			}
		}
		return authorBooks;
	}

}
