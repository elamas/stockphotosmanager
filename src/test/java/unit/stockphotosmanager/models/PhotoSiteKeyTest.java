package unit.stockphotosmanager.models;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import stockphotosmanager.models.Photo;
import stockphotosmanager.models.PhotoSiteKey;

public class PhotoSiteKeyTest {

	//en cada metodo testeo el getter y setter de una propiedad
	
	@Test
	public void photoIdTest() {
		Integer photoId = 100;
		
		PhotoSiteKey photoSiteKey = new PhotoSiteKey();
		photoSiteKey.setPhotoId(photoId);
		assertEquals(photoId, photoSiteKey.getPhotoId());
	}

	@Test
	public void siteIdTest() {
		Integer siteId = 100;
		
		PhotoSiteKey photoSiteKey = new PhotoSiteKey();
		photoSiteKey.setSiteId(siteId);
		assertEquals(siteId, photoSiteKey.getSiteId());
	}

}
