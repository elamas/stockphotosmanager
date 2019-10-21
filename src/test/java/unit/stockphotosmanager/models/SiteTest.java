package unit.stockphotosmanager.models;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import stockphotosmanager.models.Photo;
import stockphotosmanager.models.PhotoSite;
import stockphotosmanager.models.PhotoSiteKey;
import stockphotosmanager.models.Site;


public class SiteTest {

	//en cada metodo testeo el getter y setter de una propiedad
	
	@Test
	public void idTest() {
		Integer id = 100;
		
		Site site = new Site();
		site.setId(id);
		assertEquals(id, site.getId());
	}

	@Test
	public void nameTest() {
		String name = "elname";
		
		Site site = new Site();
		site.setName(name);
		assertEquals(name, site.getName());
	}
	
	@Test
	public void idNumKeywords() {
		Integer numKeywords = 33;
		
		Site site = new Site();
		site.setNumKeywords(numKeywords);
		assertEquals(numKeywords, new Integer(site.getNumKeywords()));
	}

	@Test
	public void idNumCategories() {
		Integer numCategories = 22;
		
		Site site = new Site();
		site.setNumCategories(numCategories);
		assertEquals(numCategories, new Integer(site.getNumCategories()));
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
		
		Site site = new Site();
		site.setPhotoSites(photoSites);
		assertEquals(photoSites, site.getPhotoSites());
	}


}
