package stockphotosmanager.util;

import java.util.List;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.DetectLabelsRequest;
import com.amazonaws.services.rekognition.model.DetectLabelsResult;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.Label;
import com.amazonaws.services.rekognition.model.S3Object;

public class KeywordsManager {
	
	private AmazonRekognition awsRekognition;

	public KeywordsManager() throws Exception {
		//read credentials from env variables
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
	
	public void getKeywords() {
		
		S3Object s3Object = new S3Object()
				.withBucket("qqtest20190613")//TODO
				.withName("landscape.jpg") //TODO. Probar tb con folders intermedios
				;
		
		Image image = new Image()
				.withS3Object(s3Object)
				;
		
		DetectLabelsRequest detectLabelsRequest = new DetectLabelsRequest()
				.withImage(image) //TODO
				//.withMaxLabels(null) //TODO
				//.withMinConfidence(null) //TODO
				;
		
		
		DetectLabelsResult detectLabelsResult = awsRekognition.detectLabels(detectLabelsRequest);
		
		List<Label> labels = detectLabelsResult.getLabels();
		
		System.err.println("[KeywordsManager - DetectLabels]labels: " + labels);
		
		/*
		for (Label label : labels) {
			
		}
		*/
	}
	
	public static void main(String[] args) {
		try {
			KeywordsManager keywordsManager = new KeywordsManager();
			keywordsManager.getKeywords();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

