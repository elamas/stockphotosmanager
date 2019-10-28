package stockphotosmanager.lambda;

import org.springframework.boot.SpringApplication;

import com.amazonaws.services.lambda.runtime.events.S3Event;

public class MySpringApplication extends SpringApplication {
	
	private S3Event s3event;

	public MySpringApplication(Class<UploadHandler> theClass) {
		super(theClass);
	}

	public void setS3event(S3Event s3event) {
		this.s3event = s3event;
	}
	
}
