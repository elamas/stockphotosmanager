package unit.stockphotosmanager.models;

import org.junit.Test;

import stockphotosmanager.models.Photo;
import stockphotosmanager.models.PhotoSite;
import stockphotosmanager.models.PhotoSiteKey;
import stockphotosmanager.models.Site;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

public class PhotoTest {
	
	//en cada metodo testeo el getter y setter de una propiedad
	
	@Test
	public void idTest() {
		Integer id = 100;
		
		Photo photo = new Photo();
		photo.setId(id);
		assertEquals(id, photo.getId());
	}

	@Test
	public void dvdTest() {
		String dvd = "dvd1";
		
		Photo photo = new Photo();
		photo.setDvd(dvd);
		assertEquals(dvd, photo.getDvd());
	}
	
	@Test
	public void fileNameTest() {
		String fileName = "name1";
		
		Photo photo = new Photo();
		photo.setFileName(fileName);
		assertEquals(fileName, photo.getFileName());
	}
	
	@Test
	public void uploadDateMillisTest() {
		Integer  uploadDateMillis = 1111;
		
		Photo photo = new Photo();
		photo.setUploadDateMillis(uploadDateMillis);
		assertEquals(uploadDateMillis, photo.getUploadDateMillis());
	}
	
	@Test
	public void uploadDateStringMadridTest() {
		String uploadDateStringMadrid = "lalala";
		
		Photo photo = new Photo();
		photo.setUploadDateStringMadrid(uploadDateStringMadrid);
		assertEquals(uploadDateStringMadrid, photo.getUploadDateStringMadrid());
	}

	@Test
	public void commentsTest() {
		String comments = "lalala";
		
		Photo photo = new Photo();
		photo.setComments(comments);
		assertEquals(comments, photo.getComments());
	}
	
	@Test
	public void photoSitesTest() {
		PhotoSite photoSite = new PhotoSite();
		photoSite.setComments("lala");
		
		PhotoSiteKey id = new PhotoSiteKey();
		id.setPhotoId(100);
		id.setSiteId(200);
		photoSite.setId(id);

		photoSite.setLastRevisionDateMillis(1111);
		photoSite.setLastRevisionDateStringMadrid("1111");
		
//		photoSite.setPhoto(photo);
//		photoSite.setSite(site);
		
		photoSite.setStatus(33);
		photoSite.setUploadDateMillis(1111);
		photoSite.setUploadDateStringMadrid("1111");
		
		Set<PhotoSite> photoSites = new HashSet<PhotoSite>();
		photoSites.add(photoSite);
		
		Photo photo = new Photo();
		photo.setPhotoSites(photoSites);
		assertEquals(photoSites, photo.getPhotoSites());
	}

}
