package com.QAspring.SpringBootRestService.repository;

import java.util.List;
import com.QAspring.SpringBootRestService.controller.Book;

public interface LibraryRepositoryCustom {
	List<Book> findAllByAuthor(String author, LibraryRepository repository);
}
