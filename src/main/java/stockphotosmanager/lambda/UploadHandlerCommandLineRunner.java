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
import stockphotosmanager.services.PhotoServiceImpl;

//@Component
public class UploadHandlerCommandLineRunner implements CommandLineRunner{

	//@Autowired
	private PhotoService photoService;
	
	//@Autowired
	S3EventUtil s3EventUtil;
	
	public static void startSpringApplication(String path) {
		SpringApplication app = new SpringApplication(UploadHandlerCommandLineRunner.class);
		app.setWebApplicationType(WebApplicationType.NONE);
		app.run(new String[]{path});
	}
	
	@Override
	public void run(String... args) throws Exception {
		String path = args[0];
		if (path == null) {
			throw new Exception("Empty path");
		}
		System.err.println("[UploadHandlerCommandLineRunner - run]path: " + path);
		
		//no me molan estas chapuzas, pero bueno
		if (s3EventUtil == null) {//no es null cuando viene mockeado
			s3EventUtil = new S3EventUtil();
		}
		if (photoService == null) {//no es null cuando viene mockeado
			photoService = new PhotoServiceImpl();
		}

		
		S3EventNotification s3EventNotification = null;
//		try {
////			String s3eventJson = FileUtils.readFileToString(new File("/tmp/s3event.txt"), "UTF-8");
////			s3EventNotification = S3Event.parseJson(s3eventJson);
//			s3EventNotification = s3EventUtil.getS3Event();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		System.err.println("[UploadHandlerCommandLineRunner - run]s3EventUtil: " + s3EventUtil);
		s3EventNotification = s3EventUtil.getS3Event(path);

		
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

	public void setS3EventUtil(S3EventUtil s3EventUtil) {
		this.s3EventUtil = s3EventUtil;
	}

	public void setPhotoService(PhotoService photoService) {
		this.photoService = photoService;
	}
	
}
