package stockphotosmanager.lambda;

import java.io.File;
import java.net.URLDecoder;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.stereotype.Component;

import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.event.S3EventNotification;
import com.amazonaws.services.s3.event.S3EventNotification.S3Entity;
import com.amazonaws.services.s3.event.S3EventNotification.S3EventNotificationRecord;

import stockphotosmanager.services.PhotoService;

@Component
public class UploadHandlerCommandLineRunner implements CommandLineRunner{

	@Autowired
	private PhotoService photoService;
	
	@Autowired
	S3EventUtil s3EventUtil;
	
	public static void startSpringApplication() {
		SpringApplication app = new SpringApplication(UploadHandler.class);
		app.setWebApplicationType(WebApplicationType.NONE);
		app.run(new String[]{});
	}
	
	@Override
	public void run(String... args) throws Exception {
		S3EventNotification s3EventNotification = null;
		try {
//			String s3eventJson = FileUtils.readFileToString(new File("/tmp/s3event.txt"), "UTF-8");
//			s3EventNotification = S3Event.parseJson(s3eventJson);
			s3EventNotification = s3EventUtil.getS3Event();
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		//System.err.println("[UploadHandler - run]s3event.getClass(): " + s3event.getClass());
		System.err.println("[UploadHandler - run]s3EventNotification: " + s3EventNotification);
    	//List<S3EventNotificationRecord> records = s3event.getRecords();
		List<S3EventNotificationRecord> records = s3EventNotification.getRecords();
    	if (records != null && records.size() > 0) {
    		System.err.println("******* records begin *********");
    		for (S3EventNotificationRecord record : records) {
    			S3Entity entity = record.getS3();
    			String bucketName = entity.getBucket().getName();
    			String key = entity.getObject().getKey();
    			System.err.println("bucket name: " + bucketName);
    			System.err.println("object key: " + key);
    			
    			//por si tiene espacios y otras cosas
                String keyNormalized = record.getS3().getObject().getKey().replace('+', ' ');
                keyNormalized = URLDecoder.decode(keyNormalized, "UTF-8");
                System.err.println("keyNormalized: " + keyNormalized);
                
                photoService.processPhoto(bucketName, keyNormalized);
    		}
    		System.err.println("******* records end *********");
    	} else {
    		System.err.println("No records found");
    	}
	}

}
