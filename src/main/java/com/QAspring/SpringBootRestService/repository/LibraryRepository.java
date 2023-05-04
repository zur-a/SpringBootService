package com.QAspring.SpringBootRestService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.QAspring.SpringBootRestService.controller.Book;

public interface LibraryRepository extends JpaRepository<Book, String>, LibraryRepositoryCustom {
	
}
