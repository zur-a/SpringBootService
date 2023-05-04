package com.QAspring.SpringBootRestService.repository;

import java.util.List;
import com.QAspring.SpringBootRestService.controller.Book;

public interface BookRepositoryCustom {
	List<Book> findAllByAuthor(String author, BookRepository repository);
}
