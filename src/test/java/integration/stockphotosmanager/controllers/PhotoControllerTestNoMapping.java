package integration.stockphotosmanager.controllers;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import stockphotosmanager.StockphotosmanagerApplication;
import stockphotosmanager.controllers.PhotoController;
import stockphotosmanager.models.Photo;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = StockphotosmanagerApplication.class)
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class PhotoControllerTestNoMapping {

	@Autowired
	private PhotoController photoController;
	
	@Test
	@Sql("/insert.sql")
	@Transactional
	@Rollback(true)
	public void findAllTest() {
		List<Photo> photos = photoController.findAll();
		assertTrue(photos.size() > 0);
		Photo photo = photos.get(0);
		//se podria implementar un equals en el Photo, pero lo hago asi
//		assertEquals(photo.getDvd(), "lalala");
//		assertEquals(photo.getFileName(), "lalala");
//		assertEquals(photo.getUploadDateMillis(), new Integer(1111));
//		assertEquals(photo.getUploadDateStringMadrid(), "1111");
//		assertEquals(photo.getComments(), "bcbbcbv");
		assertNotNull(photo.getDvd());
		assertNotNull(photo.getFileName());
		assertNotNull(photo.getUploadDateMillis());
		assertNotNull(photo.getUploadDateStringMadrid());
		assertNotNull(photo.getComments());
	}
}
