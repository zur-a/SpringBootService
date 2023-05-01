package com.QAspring.SpringBootRestService.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.QAspring.SpringBootRestService.controller.Library;

public class LibraryRepositoryImplementation implements LibraryRepositoryCustom {
	
	@Autowired
	LibraryRepository repository;
	
	@Override
	public List<Library> findAllByAuthor(String author) {
		List<Library> books = repository.findAll();
		List<Library> authorBooks = new ArrayList<Library>();
		
		for (int i = 0; i < books.size(); i++) {
			if(books.get(i).getAuthor().equalsIgnoreCase("Goethe")) {
				System.out.println(books.get(i).getAuthor().equalsIgnoreCase("Goethe"));
				authorBooks.add(books.get(i));
			}
		}
		return authorBooks;
	}

}
