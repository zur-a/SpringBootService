package com.QAspring.SpringBootRestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.QAspring.SpringBootRestService.repository.LibraryRepository;

@SpringBootApplication
public class SpringBootRestServiceApplication {
	
	@Autowired
	LibraryRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestServiceApplication.class, args);
	}
}
