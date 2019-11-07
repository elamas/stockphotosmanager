package stockphotosmanager.lambda;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import org.apache.commons.io.FileUtils;

import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.event.S3EventNotification;

//es una clase que me veo obligado a crear para los tests, para poder mockear en los tests
public class S3EventUtil {
	
	public S3EventUtil() {
		
	}
	
	public String saveS3Event(S3Event s3event) throws IOException {
		Random random = new Random();
		long randomLong = random.nextLong();
		StringBuilder sbPath = new StringBuilder(256);
		sbPath.append("/tmp/");
		sbPath.append(Long.toString(randomLong));
		sbPath.append(".txt");
		String path =  sbPath.toString();
		FileUtils.writeStringToFile(new File(path), s3event.toJson(), "UTF-8");
		return path;
	}
	
	public S3EventNotification getS3Event(String path) throws IOException {
		String s3eventJson = FileUtils.readFileToString(new File(path), "UTF-8");
		return(S3Event.parseJson(s3eventJson));
	}
}
