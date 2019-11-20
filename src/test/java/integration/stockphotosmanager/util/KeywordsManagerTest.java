package integration.stockphotosmanager.util;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.Label;

import stockphotosmanager.util.KeywordsManager;
import static org.junit.Assert.*;

public class KeywordsManagerTest {
	
	private static AmazonRekognition awsRekognition; 
	
	@BeforeClass
	public static void doOnce() throws Exception {
		String access_key = System.getenv("QQ_AWS_ACCESS_KEY");
		String secret_key = System.getenv("QQ_AWS_SECRET_KEY");
		System.err.println("[KeywordsManager - constructor]access_key: " + access_key);
		System.err.println("[KeywordsManager - constructor]secret_key: " + secret_key);
		if (access_key ==  null || secret_key == null) {
			throw new Exception("Unable to get credentials from env variables");
		}
		AWSCredentials userCredentials = new BasicAWSCredentials(access_key, secret_key);
		awsRekognition = AmazonRekognitionClientBuilder
		        .standard()
		        .withCredentials(new AWSStaticCredentialsProvider(userCredentials))
		        .withRegion("eu-west-1")
		        .build();
	}

	@Test
	public void getKeywords() throws Exception {
		KeywordsManager keywordsManager = new KeywordsManager(awsRekognition);
		List<String > keywords = keywordsManager.getKeywords("qqtest20190613", "landscape.jpg", 10);
		System.err.println("[KeywordsManagerTest - getKeywords]keywords: " + keywords);
		assertNotNull(keywords);
		assertTrue(keywords.size() > 0);

	}
}
