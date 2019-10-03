package unit.stockphotosmanager.models;

import org.junit.Test;

import stockphotosmanager.models.Photo;

import static org.junit.Assert.*;

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
}
