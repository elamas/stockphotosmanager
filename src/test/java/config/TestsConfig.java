package config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import stockphotosmanager.StockphotosmanagerApplication;
import stockphotosmanager.services.PhotoService;
import stockphotosmanager.services.PhotoServiceImpl;
import stockphotosmanager.util.KeywordsManager;

import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"stockphotosmanager"
		}, 
		excludeFilters={
		  @ComponentScan.Filter(type=FilterType.ASSIGNABLE_TYPE, value=StockphotosmanagerApplication.class)}
		)
@EntityScan("stockphotosmanager.models")
@EnableJpaRepositories("stockphotosmanager.repositories")
public class TestsConfig {
	
  @Bean
  public PhotoService photoService() {
      return new PhotoServiceImpl();
  }	
	
    @Bean
    public KeywordsManager keywordsManager() throws Exception {
    	System.err.println("[TestsConfig - keywordsManager()] Paso por aqui");
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
    	
    	
        return new KeywordsManager(awsRekognition);
    }
}
