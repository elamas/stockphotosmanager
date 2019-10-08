package integration.stockphotosmanager.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import stockphotosmanager.StockphotosmanagerApplication;
import stockphotosmanager.controllers.PhotoController;
import stockphotosmanager.repositories.PhotoRepository;
import stockphotosmanager.services.PhotoService;
import stockphotosmanager.services.PhotoServiceImpl;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.*;



@RunWith(SpringRunner.class)
//@WebMvcTest(PhotoController.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = StockphotosmanagerApplication.class)
@ContextConfiguration(classes=StockphotosmanagerApplication.class)
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
//@ComponentScan(basePackages = "stockphotosmanager")
@AutoConfigureMockMvc
public class PhotoControllerTestWithMapping {

    @Autowired
    private MockMvc mvc;
    
//    @Autowired
//    PhotoRepository photoRepository;
    
//    @TestConfiguration
//    //@ComponentScan(basePackages = "stockphotosmanager")
//    static class PhotoServiceImplTestContextConfiguration {
//  
//        @Bean
//        public PhotoService photoService() {
//            return new PhotoServiceImpl();
//        }
//    }
	
	@Test
	@Sql("/insert.sql")
	@Transactional
	@Rollback(true)
	public void findAllTest() throws Exception {
	    mvc.perform(get("/backend/photos")
	    	      .contentType(MediaType.APPLICATION_JSON))
	    	      .andExpect(status().isOk())
	    	      //.andExpect(jsonPath("$", hasSize(1)))
	    	      //.andExpect(jsonPath("$[0].dvd", is("lalala")));
	    	      .andExpect(jsonPath("$[0].dvd", not(nullValue())));
	    
	}
}
