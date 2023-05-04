package com.QAspring.SpringBootRestService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.QAspring.SpringBootRestService.controller.Book;

public interface BookRepository extends JpaRepository<Book, String>, BookRepositoryCustom {
	
}
