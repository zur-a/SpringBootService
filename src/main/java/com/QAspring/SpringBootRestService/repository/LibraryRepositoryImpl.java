package com.QAspring.SpringBootRestService.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.QAspring.SpringBootRestService.controller.Library;

public class LibraryRepositoryImpl implements LibraryRepositoryCustom {
	
	//@Autowired
	//LibraryRepository repository;
	
	@Override
	public List<Library> findAllByAuthor(String authorName, LibraryRepository repository) {
		List<Library> books = repository.findAll();
		List<Library> authorBooks = new ArrayList<Library>();
		for (Library book : books) {
			if (book.getAuthor().equalsIgnoreCase(authorName)) {
				authorBooks.add(book);
			}
		}
		return authorBooks;
	}

}
