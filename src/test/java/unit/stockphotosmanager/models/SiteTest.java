package unit.stockphotosmanager.models;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

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

}
