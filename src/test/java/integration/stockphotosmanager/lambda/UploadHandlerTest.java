package integration.stockphotosmanager.lambda;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.event.S3EventNotification.S3BucketEntity;
import com.amazonaws.services.s3.event.S3EventNotification.S3Entity;
import com.amazonaws.services.s3.event.S3EventNotification.S3EventNotificationRecord;
import com.amazonaws.services.s3.event.S3EventNotification.S3ObjectEntity;

import config.TestsConfig;
import stockphotosmanager.lambda.S3EventUtil;
import stockphotosmanager.lambda.UploadHandler;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = TestsConfig.class)
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class UploadHandlerTest {
	
	@MockBean
	S3EventUtil s3EventUtil;
	
	@Mock
	//@Spy
	private S3Event s3event;
	
	@Mock
	private S3BucketEntity bucket;

	@Mock
	private S3ObjectEntity s3Object;
	
	@Mock
	private S3Entity s3Entity;

	
	@Test
	public void handleRequestTest() throws Exception {
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
		String s3eventJson = new String(Files.readAllBytes(Paths.get(getClass().getResource("/s3event.json").toURI())));
		when(s3event.toJson()).thenReturn(s3eventJson);
		//mock end

		UploadHandler uploadHandler = new UploadHandler();
		String returnedString = uploadHandler.handleRequest(s3event, null);
		assertEquals(returnedString, "dummy");
	}
}
