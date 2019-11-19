package unit.stockphotosmanager.models;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import stockphotosmanager.models.Keyword;
import stockphotosmanager.models.Photo;

public class KeywordTest {

	//en cada metodo testeo el getter y setter de una propiedad
	
	@Test
	public void idTest() {
		Integer id = 100;
		
		Keyword keyword = new Keyword();
		keyword.setId(id);
		assertEquals(id, keyword.getId());
	}

	@Test
	public void dvdTest() {
		String name = "thekeyword";
		
		Keyword keyword = new Keyword();
		keyword.setName(name);
		assertEquals(name, keyword.getName());
	}

	public void photosTest() {
		Photo photo = new Photo();
		photo.setId(111);
		photo.setDvd("dvdtest");
		photo.setComments("commensttest");
		photo.setFileName("filenametest");
		photo.setUploadDateMillis(1111);
		photo.setUploadDateStringMadrid("1111");

		Set<Photo> photos = new HashSet<Photo>();
		photos.add(photo);

		Keyword keyword = new Keyword();
		keyword.setPhotos(photos);
		assertEquals(photos, keyword.getPhotos());
	}
}
