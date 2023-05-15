package com.QAspring.SpringBootRestService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.QAspring.SpringBootRestService.service.BookService;

@SpringBootTest
class SpringBootRestServiceApplicationTests {
	
	@Autowired
	BookService bookservice;
	
	@Test
	void contextLoads() {
	}
	
	//BookService methods
	
	@Test
	public void checkBuildId() {
		assertEquals("978-85-4400-000-714", bookservice.idBuilder("978-85-4400-000-7", 14));
	}
	
	@Test
	public void checkBookExists() {
		assertTrue(bookservice.checkBookExists("978-85-4400-000-714"));
	}
	
	@Test
	public void checkBookDoesNotExists() {
		assertFalse(bookservice.checkBookExists("000-00-0000-000-001"));
	}
	
	

}
