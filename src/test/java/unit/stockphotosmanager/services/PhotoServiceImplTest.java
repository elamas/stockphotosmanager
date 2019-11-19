package unit.stockphotosmanager.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.amazonaws.services.rekognition.model.Label;

import config.TestsConfig;
import stockphotosmanager.repositories.PhotoRepository;
import stockphotosmanager.services.PhotoService;
import stockphotosmanager.services.PhotoServiceImpl;
import stockphotosmanager.util.KeywordsManager;
import stockphotosmanager.StockphotosmanagerApplication;
import stockphotosmanager.models.Photo;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
//@ContextConfiguration(classes=TestsConfig.class)
public class PhotoServiceImplTest {
	
    @TestConfiguration
    static class PhotoServiceImplTestContextConfiguration {
  
        @Bean
        public PhotoService photoService() {
            return new PhotoServiceImpl();
        }
        
//        @Bean
//        public KeywordsManager keywordsManager() {
//            return new KeywordsManager();
//        }
        
    }
	
	@MockBean
	private PhotoRepository photoRepository;
	
	@Autowired
	private PhotoService photoService;
	
	@MockBean
	private KeywordsManager keywordsManager;
	
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

	@Test
	public void processPhoto() {
		//mock begin
		List<Label> labels = new ArrayList<Label>();
		labels.add(new Label().withName("lalala"));
		when(keywordsManager.getKeywords()).thenReturn(labels);
		//mock end
		photoService.processPhoto("elbucketName", "lakeyNormalized");
	}

}
