package integration.stockphotosmanager.models;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import stockphotosmanager.StockphotosmanagerApplication;
import stockphotosmanager.models.Photo;
import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

@RunWith(SpringRunner.class)
@DataJpaTest
//@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE) //necesaria si quiero probar contra mysql
//@Transactional(propagation = Propagation.NOT_SUPPORTED)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = StockphotosmanagerApplication.class)
@ContextConfiguration(classes=StockphotosmanagerApplication.class)
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class PhotoTest {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void saveTest() {
		Photo photo = new Photo();
		photo.setDvd("dvdtest");
		photo.setComments("commensttest");
		photo.setFileName("filenametest");
		photo.setUploadDateMillis(1111);
		photo.setUploadDateStringMadrid("1111");
		
		Photo savedPhoto = this.entityManager.persistAndFlush(photo);
		//Photo savedPhoto = this.entityManager.persist(photo);
		assertEquals(savedPhoto.getDvd(), photo.getDvd());
		
	}
	
	/*
	@Test
	public void saveTest_throwException() {
		this.thrown.expect(PersistenceException.class);
		
		Photo photo = new Photo();
		photo.setDvd("dvdtest");
		
		Photo savedPhoto = this.entityManager.persistAndFlush(photo);
		
	}
	*/
}
