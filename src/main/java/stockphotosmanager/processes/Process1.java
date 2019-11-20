package stockphotosmanager.processes;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.Label;

import stockphotosmanager.util.KeywordsManager;

public class Process1 implements CommandLineRunner {
	
	private static AmazonRekognition getAmazonRekognition() throws Exception {
		String access_key = System.getenv("QQ_AWS_ACCESS_KEY");
		String secret_key = System.getenv("QQ_AWS_SECRET_KEY");
		System.err.println("[KeywordsManager - constructor]access_key: " + access_key);
		System.err.println("[KeywordsManager - constructor]secret_key: " + secret_key);
		if (access_key ==  null || secret_key == null) {
			throw new Exception("Unable to get credentials from env variables");
		}
		AWSCredentials userCredentials = new BasicAWSCredentials(access_key, secret_key);
		AmazonRekognition awsRekognition = AmazonRekognitionClientBuilder
		        .standard()
		        .withCredentials(new AWSStaticCredentialsProvider(userCredentials))
		        .withRegion("eu-west-1")
		        .build();
		return awsRekognition;
	}

	@Override
	public void run(String... args) throws Exception {
		try {
			KeywordsManager keywordsManager = new KeywordsManager(getAmazonRekognition());
			List<String> kewwords = keywordsManager.getKeywords("qqtest20190613", "landscape.jpg", 10);//TODO que el 10 lo pille de properties
			System.err.println("[KeywordsManager - DetectLabels]kewwords: " + kewwords);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    public static void main(String[] args) throws Exception {
    	System.err.println("[Process1] Estoy en el Process1");
    	SpringApplication app = new SpringApplication(Process1.class);
    	app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);
    }


}
