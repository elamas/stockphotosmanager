package unit.stockphotosmanager.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import stockphotosmanager.repositories.PhotoRepository;
import stockphotosmanager.services.PhotoService;
import stockphotosmanager.services.PhotoServiceImpl;
import stockphotosmanager.models.Photo;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class PhotoServiceImplTest {
	
    @TestConfiguration
    static class PhotoServiceImplTestContextConfiguration {
  
        @Bean
        public PhotoService photoService() {
            return new PhotoServiceImpl();
        }
    }
	
	@MockBean
	private PhotoRepository photoRepository;
	
	@Autowired
	private PhotoService photoService;
	
	@Test
	public void findAllTest() {
		//mock begin
		List<Photo> photos = new ArrayList<Photo>();
		Photo photo1 = new Photo();
		photo1.setDvd("dvd1");
		photos.add(photo1);
		when(photoRepository.findAll()).thenReturn(photos);
		//mock end
		
		List<Photo> photosReturned = photoService.findAll();
		assertEquals(photos, photosReturned);
	}

}
