package integration.stockphotosmanager.services;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.*;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import stockphotosmanager.StockphotosmanagerApplication;
import stockphotosmanager.models.Photo;
import stockphotosmanager.repositories.PhotoRepository;
import stockphotosmanager.services.PhotoService;
import stockphotosmanager.services.PhotoServiceImpl;

@RunWith(SpringRunner.class)
//@DataJpaTest
//@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE) //necesaria si quiero probar contra mysql
//@ContextConfiguration(classes=StockphotosmanagerApplication.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = StockphotosmanagerApplication.class)
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class PhotoServiceImplTest {

	/*
    @TestConfiguration
    //@ComponentScan(basePackages = "stockphotosmanager")
    static class PhotoServiceImplTestContextConfiguration {
  
        @Bean
        public PhotoService photoService() {
            return new PhotoServiceImpl();
        }
    }
    */
    
	@Autowired
	private PhotoService photoService;
	
	@Test
	@Sql("/insert.sql")
	@Transactional
	@Rollback(true)
	public void findAllTest() {
		List<Photo> photos = photoService.findAll();
		assertEquals(photos.size(), 1);
		Photo photo = photos.get(0);
		//se podria implementar un equals en el Photo, pero lo hago asi
		assertEquals(photo.getDvd(), "lalala");
		assertEquals(photo.getFileName(), "lalala");
		assertEquals(photo.getUploadDateMillis(), new Integer(1111));
		assertEquals(photo.getUploadDateStringMadrid(), "1111");
		assertEquals(photo.getComments(), "bcbbcbv");
	}

}
