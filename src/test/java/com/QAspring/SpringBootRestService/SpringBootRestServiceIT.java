package com.QAspring.SpringBootRestService;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.QAspring.SpringBootRestService.controller.Book;

@SpringBootTest
public class SpringBootRestServiceIT {
	//Integration test cases
	//Uses TestRestTemplate
		//Rest Assured is a viable option
	//Active profile in application.properties should be h2 for the tests to run
	
	public Book buildBook() {
		Book book = new Book();
		book.setTitle("O divã ocidento-oriental");
		book.setAuthor("Goethe");
		book.setIsbn("978-85-7448-307-8");
		book.setAisle(42);
		book.setId("978-85-7448-307-842");
		return book;
	}
	
	@Test
	public void addBookTest() throws Exception {
		TestRestTemplate rest = new TestRestTemplate();
		
		//Creating a header object to send to HttpEntity
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		
		//Creating the request object
		HttpEntity<Book> request = new HttpEntity<Book>(buildBook(), header);
		
		//Creating the response as a string given the url of the service, the request object and
		ResponseEntity<String> response = rest.postForEntity("http://localhost:8080/addBook", request, String.class);
		
		//Asserting the status code and the id returned in the unique field
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(buildBook().getId(), response.getHeaders().get("unique").get(0));

	}
	
	@Test
	public void getBookByAuthorTest() throws Exception {
		TestRestTemplate rest = new TestRestTemplate();
		
		//Creating the response as a string given the url of the service
		ResponseEntity<String> response = rest.getForEntity("http://localhost:8080/getBook/author?authorName=Horácio", String.class);
	
		String expectedResult = 
				"[\r\n"
				+ "    {\r\n"
				+ "        \"id\": \"978-65-8823-952-005\",\r\n"
				+ "        \"title\": \"Arte poética\",\r\n"
				+ "        \"isbn\": \"978-65-8823-952-0\",\r\n"
				+ "        \"aisle\": 5,\r\n"
				+ "        \"author\": \"Horácio\"\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "        \"id\": \"978-65-5525-078-705\",\r\n"
				+ "        \"title\": \"Odes\",\r\n"
				+ "        \"isbn\": \"978-65-5525-078-7\",\r\n"
				+ "        \"aisle\": 5,\r\n"
				+ "        \"author\": \"Horácio\"\r\n"
				+ "    }\r\n"
				+ "]";
		
		//Asserting the expected result with the response retuned
		JSONAssert.assertEquals(expectedResult, response.getBody(), false);
	}
	
	
}
