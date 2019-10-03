package unit.stockphotosmanager.controllers;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import stockphotosmanager.controllers.PhotoController;
import stockphotosmanager.models.Photo;
import stockphotosmanager.services.PhotoService;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class PhotoControllerTest {
	
    @TestConfiguration
    static class PhotoControllerTestContextConfiguration {
  
        @Bean
        public PhotoController photoController() {
            return new PhotoController();
        }
    }
	
	@MockBean
	private PhotoService photoService;
	
	@Autowired
	private PhotoController photoController;
	
	@Test
	public void findAllTest() {
		//mock begin
		List<Photo> photos = new ArrayList<Photo>();
		Photo photo1 = new Photo();
		photo1.setDvd("dvd1");
		photos.add(photo1);
		when(photoService.findAll()).thenReturn(photos);
		//mock end
		
		List<Photo> photosReturned = photoController.findAll();
		assertEquals(photos, photosReturned);
	}

}
