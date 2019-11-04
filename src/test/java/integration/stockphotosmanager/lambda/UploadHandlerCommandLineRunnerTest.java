package integration.stockphotosmanager.lambda;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
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
import stockphotosmanager.lambda.S3EventUtil;
import stockphotosmanager.lambda.UploadHandler;
import stockphotosmanager.lambda.UploadHandlerCommandLineRunner;
import stockphotosmanager.services.PhotoService;
import stockphotosmanager.services.PhotoServiceImpl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = StockphotosmanagerApplication.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = TestsConfig.class)
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class UploadHandlerCommandLineRunnerTest {
	
	//lo dejo mockeado por problemas
	@MockBean
	S3EventUtil s3EventUtil;
	
	@Mock
	private S3Event s3event;
	
	@Mock
	private S3BucketEntity bucket;

	@Mock
	private S3ObjectEntity s3Object;
	
	@Mock
	private S3Entity s3Entity;
	
	@Autowired
	private PhotoService photoService;
	
//	@Autowired
//	private UploadHandlerCommandLineRunner uploadHandlerCommandLineRunner;
	
	
    @TestConfiguration
//    @ComponentScan(basePackages = {"stockphotosmanager.services"
//	}
//    )
    static class UploadHandlerTestContextConfiguration {
  
//        @Bean
//        public UploadHandlerCommandLineRunner uploadHandlerCommandLineRunner() {
//            return new UploadHandlerCommandLineRunner();
//        }
        
//        @Bean
//        public PhotoService photoService() {
//            return new PhotoServiceImpl();
//        }
    }

	@Test
	public void runTest() throws Exception {
		System.err.println("[UploadHandlerCommandLineRunnerTest - runTest] Traza 1");
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
		
		when(s3EventUtil.getS3Event()).thenReturn(s3event);
		//mock end
		
//		S3EventUtil s3EventUtil = new S3EventUtil();
//		s3EventUtil.saveS3Event(s3event);
		String[] args = null;
		
		UploadHandlerCommandLineRunner uploadHandlerCommandLineRunner = new UploadHandlerCommandLineRunner();
		uploadHandlerCommandLineRunner.setS3EventUtil(s3EventUtil);
		uploadHandlerCommandLineRunner.setPhotoService(photoService);
		uploadHandlerCommandLineRunner.run(args);
	}
}
