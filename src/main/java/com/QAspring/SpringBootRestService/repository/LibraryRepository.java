package com.QAspring.SpringBootRestService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.QAspring.SpringBootRestService.controller.Library;

public interface LibraryRepository extends JpaRepository<Library, String>, LibraryRepositoryCustom {
	
}
