package integration.stockphotosmanager.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import config.TestsConfig;
import stockphotosmanager.StockphotosmanagerApplication;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = StockphotosmanagerApplication.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestsConfig.class)
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class PhotoControllerTestWithTestRestTemplate {

	@Autowired
	private TestRestTemplate template;
	
	@Test
	@Sql("/insert.sql")
	@Transactional
	@Rollback(true)
	public void findAllTest() throws Exception {
		ResponseEntity<String> response = template.getForEntity("/backend/photos",
	            String.class);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		//no testeo con json path por no perder tiempo
		System.err.println("**************** [PhotoControllerTestWithTestRestTemplate - findAllTest] response.getBody(): " + response.getBody());
		assertTrue(response.getBody().indexOf("amano") != -1);
		
		//jsonPath("$", hasSize(1))
	    //assertThat(response.getBody(), equalTo("Greetings from Spring Boot!"));
	}
}
