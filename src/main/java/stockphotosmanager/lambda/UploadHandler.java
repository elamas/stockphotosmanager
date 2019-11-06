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

//@Component
//@Profile("!test")
//@ComponentScan(basePackages = {"stockphotosmanager.services"
//}
//)
//@ComponentScan(basePackages = {"stockphotosmanager"
//}
////, 
////excludeFilters={
////  @ComponentScan.Filter(type=FilterType.ASSIGNABLE_TYPE, value=StockphotosmanagerApplication.class)}
//)
//@EnableJpaRepositories("stockphotosmanager.repositories")
public class UploadHandler implements RequestHandler<S3Event, String> {
	
//	private S3Event s3event;
//	private Context context;
//	
//	@Autowired
//	private PhotoService photoService;
	
//    @TestConfiguration
//    static class UploadHandlerTestContextConfiguration {
//        @Bean
//        public PhotoService photoService() {
//            return new PhotoServiceImpl();
//        }
//    }
	
//	@Autowired
	//SpringApplication app;
	//MySpringApplication app;
	
	S3EventUtil s3EventUtil = new S3EventUtil();

	@Override
	public String handleRequest(S3Event s3event, Context context) {
		System.err.println("[UploadHandler - handleRequest] Paso por aqui");		
//		this.s3event = s3event;
//		this.context = context;
		
		//guardamos en disco el s3event
		try {
			//FileUtils.writeStringToFile(new File("/tmp/s3event.txt"), s3event.toJson(), "UTF-8");
			s3EventUtil.saveS3Event(s3event);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		UploadHandlerCommandLineRunner.startSpringApplication();
        return "dummy";
	}

	/*
	//usado para testing
	public void setS3event(S3Event s3event) {
		this.s3event = s3event;
	}

	//usado para testing
	public void setApp(SpringApplication app) {
		throw(new RuntimeException("Lanzo esta excepcion temporalmente para ver si se usa y que hago en ese caso"));
		//this.app = app;
	}
	*/

}
