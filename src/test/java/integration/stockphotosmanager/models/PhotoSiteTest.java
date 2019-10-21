package integration.stockphotosmanager.models;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import stockphotosmanager.StockphotosmanagerApplication;
import stockphotosmanager.models.Photo;
import stockphotosmanager.models.PhotoSite;
import stockphotosmanager.models.PhotoSiteKey;
import stockphotosmanager.models.Site;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE) //necesaria si quiero probar contra mysql
@ContextConfiguration(classes=StockphotosmanagerApplication.class)
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class PhotoSiteTest {

	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void saveTest() {
		//inserto una photo
		Photo photo = new Photo();
		photo.setDvd("dvdtest");
		photo.setComments("commensttest");
		photo.setFileName("filenametest");
		photo.setUploadDateMillis(1111);
		photo.setUploadDateStringMadrid("1111");
		
		Photo savedPhoto = this.entityManager.persistAndFlush(photo);

		//inserto un site
		Site site = new Site();
		site.setName("elname");
		site.setNumKeywords(33);
		site.setNumCategories(22);
		
		Site savedSite = this.entityManager.persistAndFlush(site);

		PhotoSite photoSite = new PhotoSite();
		photoSite.setComments("lala");
		
		PhotoSiteKey id = new PhotoSiteKey();
		id.setPhotoId(savedPhoto.getId());
		id.setSiteId(savedSite.getId());
		photoSite.setId(id);

		photoSite.setLastRevisionDateMillis(1111);
		photoSite.setLastRevisionDateStringMadrid("1111");
		
		photoSite.setPhoto(photo);
		photoSite.setSite(site);
		
		photoSite.setStatus(33);
		photoSite.setUploadDateMillis(1111);
		photoSite.setUploadDateStringMadrid("1111");
		
		PhotoSite savedPhotoSite = this.entityManager.persistAndFlush(photoSite);
		assertEquals(savedPhotoSite, photoSite);
	}
}
