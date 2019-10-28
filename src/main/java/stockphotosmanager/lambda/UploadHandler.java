package stockphotosmanager.lambda;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URLDecoder;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.event.S3EventNotification;
import com.amazonaws.services.s3.event.S3EventNotification.S3Entity;
import com.amazonaws.services.s3.event.S3EventNotification.S3EventNotificationRecord;

import stockphotosmanager.StockphotosmanagerApplication;
import stockphotosmanager.services.PhotoService;
import stockphotosmanager.services.PhotoServiceImpl;

@Component
//@Profile("!test")
//@ComponentScan(basePackages = {"stockphotosmanager.services"
//}
//)
@ComponentScan(basePackages = {"stockphotosmanager"
}
//, 
//excludeFilters={
//  @ComponentScan.Filter(type=FilterType.ASSIGNABLE_TYPE, value=StockphotosmanagerApplication.class)}
)
//@EnableJpaRepositories("stockphotosmanager.repositories")
public class UploadHandler implements RequestHandler<S3Event, String>, CommandLineRunner{
	
	private S3Event s3event;
	private Context context;
	
	@Autowired
	private PhotoService photoService;
	
//    @TestConfiguration
//    static class UploadHandlerTestContextConfiguration {
//        @Bean
//        public PhotoService photoService() {
//            return new PhotoServiceImpl();
//        }
//    }
	
//	@Autowired
	SpringApplication app;
	//MySpringApplication app;

	@Override
	public String handleRequest(S3Event s3event, Context context) {
		System.err.println("[UploadHandler - handleRequest] Paso por aqui");		
		this.s3event = s3event;
		this.context = context;
		
		//guardamos en disco el s3event
		try {
			FileUtils.writeStringToFile(new File("/tmp/s3event.txt"), s3event.toJson(), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		System.err.println("[UploadHandler - handleRequest] guardamos en disco el s3event");
//		ObjectOutputStream oos = null;
//		FileOutputStream fos = null;
//		try {
//			fos = new FileOutputStream("/tmp/s3event.txt");  
//			oos = new ObjectOutputStream(fos);  
//			oos.writeObject(s3event);  
//			oos.flush();  
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (fos != null) {
//					fos.close();
//				}
//			} catch (Exception e2) {
//				e2.printStackTrace();
//			}
//			try {
//				if (oos != null) {
//					oos.close();
//				}
//			} catch (Exception e2) {
//				e2.printStackTrace();
//			}
//		}
		
		
    	//SpringApplication app = new SpringApplication(UploadHandler.class);
		if (app == null) {//no es un test unitario y por tanto no pilla el SpringApplication autowired
			System.err.println("[UploadHandler - handleRequest] No es un test unitario");
			app = new SpringApplication(UploadHandler.class);
			//app = new MySpringApplication(UploadHandler.class);
			//app.setS3event(s3event);
			app.setWebApplicationType(WebApplicationType.NONE);
		}
    	String[] args = null;
    	//new Object[]{null}
        //app.run(args);
    	app.run(new String[]{});
        return "dummy";
	}
	
	@Override
	public void run(String... args) throws Exception {
//		try {
//			throw new Exception("expepcion de pruebas");
//		} catch (Exception e) {
//			System.err.println("++++++++ Exception begin ++++++++++");
//			e.printStackTrace();
//			System.err.println("++++++++ Exception end ++++++++++");
//		}
		
		//leemos de disco el s3event
//		System.err.println("[UploadHandler - handleRequest] leemos de disco el s3event");
//		ObjectInputStream ois = null;
//		FileInputStream fis = null;
//		try {
//			fis = new FileInputStream("/tmp/s3event.txt");  
//			ois = new ObjectInputStream(fis);  
//			s3event=(S3Event)ois.readObject();  
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (fis != null) {
//					fis.close();
//				}
//			} catch (Exception e2) {
//				e2.printStackTrace();
//			}
//			try {
//				if (ois != null) {
//					ois.close();
//				}
//			} catch (Exception e2) {
//				e2.printStackTrace();
//			}
//		}
		S3EventNotification s3EventNotification = null;
		try {
			String s3eventJson = FileUtils.readFileToString(new File("/tmp/s3event.txt"), "UTF-8");
			s3EventNotification = S3Event.parseJson(s3eventJson);
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

	//usado para testing
	public void setS3event(S3Event s3event) {
		this.s3event = s3event;
	}

	//usado para testing
	public void setApp(SpringApplication app) {
		throw(new RuntimeException("Lanzo esta excepcion temporalmente para ver si se usa y que hago en ese caso"));
		//this.app = app;
	}
	

}
