package com.QAspring.SpringBootRestService;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
	
	@Test
	public void checkBuildId() {
		assertEquals("978-85-4400-000-714", bookservice.idBuilder("978-85-4400-000-7", 14));
	}

}
