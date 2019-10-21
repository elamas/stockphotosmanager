package unit.stockphotosmanager.models;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import stockphotosmanager.models.Photo;
import stockphotosmanager.models.PhotoSite;
import stockphotosmanager.models.PhotoSiteKey;
import stockphotosmanager.models.Site;

public class PhotoSiteTest {
	
	//en cada metodo testeo el getter y setter de una propiedad
	
	@Test
	public void idTest() {
		PhotoSiteKey photoSiteKey = new PhotoSiteKey();
		photoSiteKey.setPhotoId(100);
		photoSiteKey.setSiteId(200);
		
		PhotoSite photoSite = new PhotoSite();
		photoSite.setId(photoSiteKey);
		assertEquals(photoSiteKey, photoSite.getId());
	}

	@Test
	public void photoTest() {
		Photo photo = new Photo();
		photo.setId(100);
		
		PhotoSite photoSite = new PhotoSite();
		photoSite.setPhoto(photo);
		assertEquals(photo, photoSite.getPhoto());
	}
	
	@Test
	public void siteTest() {
		Site site = new Site();
		site.setId(100);
		
		PhotoSite photoSite = new PhotoSite();
		photoSite.setSite(site);
		assertEquals(site, photoSite.getSite());
	}
	
	@Test
	public void statusTest() {
		Integer status = 33;
		
		PhotoSite photoSite = new PhotoSite();
		photoSite.setStatus(status);
		assertEquals(status, photoSite.getStatus());
	}

	@Test
	public void uploadDateMillisTest() {
		Integer uploadDateMillis = 33;
		
		PhotoSite photoSite = new PhotoSite();
		photoSite.setUploadDateMillis(uploadDateMillis);
		assertEquals(uploadDateMillis, photoSite.getUploadDateMillis());
	}
	
	@Test
	public void uploadDateStringMadridTest() {
		String uploadDateStringMadrid = "lalala";
		
		PhotoSite photoSite = new PhotoSite();
		photoSite.setUploadDateStringMadrid(uploadDateStringMadrid);
		assertEquals(uploadDateStringMadrid, photoSite.getUploadDateStringMadrid());
	}

	@Test
	public void lastRevisionDateMillisTest() {
		Integer lastRevisionDateMillis = 33;
		
		PhotoSite photoSite = new PhotoSite();
		photoSite.setLastRevisionDateMillis(lastRevisionDateMillis);
		assertEquals(lastRevisionDateMillis, photoSite.getLastRevisionDateMillis());
	}
	
	@Test
	public void lastRevisionDateStringMadridTest() {
		String lastRevisionDateStringMadrid = "lalala";
		
		PhotoSite photoSite = new PhotoSite();
		photoSite.setLastRevisionDateStringMadrid(lastRevisionDateStringMadrid);
		assertEquals(lastRevisionDateStringMadrid, photoSite.getLastRevisionDateStringMadrid());
	}

	@Test
	public void commentsTest() {
		String comments = "lalala";
		
		PhotoSite photoSite = new PhotoSite();
		photoSite.setComments(comments);
		assertEquals(comments, photoSite.getComments());
	}

}
