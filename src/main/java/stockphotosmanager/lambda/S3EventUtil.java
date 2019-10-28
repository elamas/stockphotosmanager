package stockphotosmanager.lambda;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.event.S3EventNotification;

//es una clase que me veo obligado a crear para los tests, para poder mockear en los tests
public class S3EventUtil {
	public S3EventUtil() {
		
	}
	
	public void saveS3Event(S3Event s3event) throws IOException {
		FileUtils.writeStringToFile(new File("/tmp/s3event.txt"), s3event.toJson(), "UTF-8");
	}
	
	public S3EventNotification getS3Event() throws IOException {
		String s3eventJson = FileUtils.readFileToString(new File("/tmp/s3event.txt"), "UTF-8");
		return(S3Event.parseJson(s3eventJson));
	}
}
