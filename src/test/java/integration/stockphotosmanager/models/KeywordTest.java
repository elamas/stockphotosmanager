package integration.stockphotosmanager.models;

import static org.junit.Assert.assertEquals;

import javax.persistence.PersistenceException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import stockphotosmanager.StockphotosmanagerApplication;
import stockphotosmanager.models.Keyword;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE) //necesaria si quiero probar contra mysql
@ContextConfiguration(classes=StockphotosmanagerApplication.class)
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class KeywordTest {

	@Autowired
	private TestEntityManager entityManager;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void saveTest() {
		Keyword keyword = new Keyword();
		keyword.setName("thekeyword");
		
		Keyword savedKeyword = this.entityManager.persistAndFlush(keyword);
		System.err.println("[KeywordTest - saveTest]savedKeyword.getId(): " + savedKeyword.getId());
		assertEquals(savedKeyword.getName(), keyword.getName());
	}
	
	@Test
	public void saveTest_throwException() {
		this.thrown.expect(PersistenceException.class);
		
		Keyword keyword = new Keyword();
		
		Keyword savedKeyword = this.entityManager.persistAndFlush(keyword);
		
	}

}
