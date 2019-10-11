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
import stockphotosmanager.models.Site;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE) //necesaria si quiero probar contra mysql
@ContextConfiguration(classes=StockphotosmanagerApplication.class)
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class SiteTest {

	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void saveTest() {
		Site site = new Site();
		site.setName("elname");
		site.setNumKeywords(33);
		site.setNumCategories(22);
		
		Site savedSite = this.entityManager.persistAndFlush(site);
		assertEquals(savedSite.getName(), site.getName());
	}
}
