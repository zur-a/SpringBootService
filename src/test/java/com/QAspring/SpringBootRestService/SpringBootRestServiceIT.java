package com.QAspring.SpringBootRestService;

import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class SpringBootRestServiceIT {
	//Integration test cases
	//Uses TestRestTemplate
		//Rest Assured is a viable option
	//Active profile in application.properties should be h2 for the tests to run
	@Test
	public void getBookByAuthorTest() throws Exception {
		TestRestTemplate rest = new TestRestTemplate();
		
		//Creating the response as a string given the url of the service
		ResponseEntity<String> response = rest.getForEntity("http://localhost:8080/getBook/author?authorName=Horácio", String.class);
		
		//Printing the status code
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody());
		
		//
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
		
		//Asserting
		JSONAssert.assertEquals(expectedResult, response.getBody(), false);
	}
	
	
}
