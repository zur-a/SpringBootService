package com.QAspring.SpringBootRestService.repository;

import java.util.List;
import com.QAspring.SpringBootRestService.controller.Library;

public interface LibraryRepositoryCustom {
	List<Library> findAllByAuthor(String author, LibraryRepository repository);
}
