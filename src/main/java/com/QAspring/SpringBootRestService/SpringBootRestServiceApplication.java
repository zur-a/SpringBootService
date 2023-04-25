package com.QAspring.SpringBootRestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import com.QAspring.SpringBootRestService.controller.Library;
import com.QAspring.SpringBootRestService.repository.LibraryRepository;

@SpringBootApplication
public class SpringBootRestServiceApplication implements CommandLineRunner{
	
	@Autowired
	LibraryRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestServiceApplication.class, args);
	}
	
	@Override
	public void run(String[] args) {
		Library lib = repository.findById("978-85-7326-360-242").get();
		System.out.println(lib.getBook_name());
	}
}
