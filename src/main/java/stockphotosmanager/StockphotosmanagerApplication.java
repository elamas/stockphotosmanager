package stockphotosmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;

import stockphotosmanager.util.KeywordsManager;

@SpringBootApplication
public class StockphotosmanagerApplication {
	
    @Bean
    public AmazonRekognition awsRekognition() throws Exception {
    	System.err.println("[StockphotosmanagerApplication - awsRekognition()] Paso por aqui");
		String access_key = System.getenv("QQ_AWS_ACCESS_KEY");
		String secret_key = System.getenv("QQ_AWS_SECRET_KEY");
		System.err.println("[TestsConfig - keywordsManager()]access_key: " + access_key);
		System.err.println("[TestsConfig - keywordsManager()]secret_key: " + secret_key);
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

	public static void main(String[] args) {
		SpringApplication.run(StockphotosmanagerApplication.class, args);
	}

}
