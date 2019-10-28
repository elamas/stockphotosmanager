package integration.stockphotosmanager.lambda;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.event.S3EventNotification.S3BucketEntity;
import com.amazonaws.services.s3.event.S3EventNotification.S3Entity;
import com.amazonaws.services.s3.event.S3EventNotification.S3EventNotificationRecord;
import com.amazonaws.services.s3.event.S3EventNotification.S3ObjectEntity;

import config.TestsConfig;
import stockphotosmanager.StockphotosmanagerApplication;
import stockphotosmanager.lambda.UploadHandler;
import stockphotosmanager.services.PhotoService;
import stockphotosmanager.services.PhotoServiceImpl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

//@RunWith(MockitoJUnitRunner.class)
@RunWith(SpringRunner.class)
@ContextConfiguration(classes=TestsConfig.class)
@EnableJpaRepositories("stockphotosmanager.repositories")

//@DataJpaTest
//@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE) //necesaria si quiero probar contra mysql
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
//@ActiveProfiles("test")
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = StockphotosmanagerApplication.class)
public class UploadHandlerTest {
	
	@Mock
	private S3Event s3event;
	
	@Mock
	private S3BucketEntity bucket;

	@Mock
	private S3ObjectEntity s3Object;
	
	@Mock
	private S3Entity s3Entity;
	
	@Autowired
	private UploadHandler handler;
	
//    @TestConfiguration
//    static class UploadHandlerTestContextConfiguration {
//    	
////        @Bean
////        public SpringApplication springApplication() {
////            return new SpringApplication();
////        }
//  
////        @Bean
////        public UploadHandler uploadHandler() {
////            return new UploadHandler();
////        }
////        
////        @Bean
////        public PhotoService photoService() {
////            return new PhotoServiceImpl();
////        }
//    }

	//@Test
	public void dummy() {
		System.err.println("[UploadHandlerTest - dummy]");
	}
	
	@Test
	public void runTest() throws Exception {
		System.err.println("[UploadHandlerTest - runTest]Traza 1");
		//mock begin
		when(bucket.getName()).thenReturn("elbucket");
		when(s3Object.getKey()).thenReturn("lakey");
		
		when(s3Entity.getBucket()).thenReturn(bucket);
		when(s3Entity.getObject()).thenReturn(s3Object);
		
		S3EventNotificationRecord record = new S3EventNotificationRecord(
				null,//String awsRegion
				null,//String eventName
				null,//String eventSource
				null,//String eventTime
				null,//String eventVersion
				null,//S3EventNotification.RequestParametersEntity requestParameters
				null,//S3EventNotification.ResponseElementsEntity responseElements
				s3Entity,//S3EventNotification.S3Entity s3
				null,//S3EventNotification.UserIdentityEntity userIdentity
				null)//S3EventNotification.GlacierEventDataEntity glacierEventData
				;

		List<S3EventNotificationRecord> records = new ArrayList<S3EventNotificationRecord>();
		records.add(record);
		
		//when(s3event.getRecords()).thenReturn(null);
		when(s3event.getRecords()).thenReturn(records);
		
		//mock end
		
		//UploadHandler handler = new UploadHandler();
		System.err.println("[UploadHandlerTest - runTest]s3event: " + s3event);
		handler.setS3event(s3event);
		String[] args = null;
		handler.run(args);
		handler.setS3event(null);//para dejarlo vacio por si acaso se utilizase el handler en otro metodo de test
	}
	
	@Test
	public void handleRequestTest() {
		String returnedString = handler.handleRequest(s3event, null);
		assertEquals(returnedString, "dummy");
	}
}
