package stockphotosmanager.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.stereotype.Component;

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

@Component
public class KeywordsManager {
	
	private AmazonRekognition awsRekognition;
	

	public KeywordsManager(AmazonRekognition awsRekognition) throws Exception {
		this.awsRekognition = awsRekognition;
	}
	/*
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
	*/
	
	public List<String> getKeywords(String bucket, String imageKey, int maxKeywords) {
		List<Label> labelList = getLabels(bucket, imageKey);
		if (labelList != null && labelList.size() > 0) {
			Map<String, Label> labelMap = getDistinctLabels(labelList);
			List<Label> sortedLabelList = sort(labelMap);
			List<String> keywords = new ArrayList<String>();
			for(Label label : sortedLabelList) {
				keywords.add(label.getName());
				if (keywords.size() >= maxKeywords) {
					break;
				}
			}
			return keywords;
		} else {
			System.err.println("[KeywordsManager - getKeywords] Empty keywords");
			return null;
		}
	}
	
	//devolvemos un List ordenado
	private List<Label> sort(Map<String, Label> labelMap) {
		List<Label> labelList = new ArrayList<Label>();
		Set<Entry<String, Label>> entrySet = labelMap.entrySet();
		for (Entry<String, Label> entry : entrySet) {
			labelList.add(entry.getValue());
		}
		labelList.sort(new Comparator<Label>(){
		    @Override
		    public int compare(Label l1, Label l2) {
		        return l1.getConfidence().compareTo(l2.getConfidence());
		    }
		});
		return labelList;
	}
	
	//las metemos en un map sin repetidos. Si habia repetidos metemos la de mayor confidence
	private Map<String, Label> getDistinctLabels(List<Label> labelList) {
		Map<String, Label> labelMap = new HashMap<String, Label>();
		//las metemos en un map sin repetidos
		for (Label label : labelList) {
			Label foundLabel = labelMap.get(label.getName());
			//Si ya existe la machaca solo si tiene mas confianza.
			if (foundLabel == null || foundLabel.getConfidence() < label.getConfidence()) {
				labelMap.put(label.getName(), label);
			}
		}
		return labelMap;
	}
	
	private List<Label> getLabels(String bucket, String imageKey) {
		
		S3Object s3Object = new S3Object()
				.withBucket(bucket)
				.withName(imageKey) //TODO. Probar tb con folders intermedios
				;
		
		Image image = new Image()
				.withS3Object(s3Object)
				;
		
		DetectLabelsRequest detectLabelsRequest = new DetectLabelsRequest()
				.withImage(image)
				//.withMaxLabels(null)
				//.withMinConfidence(null)
				;
		
		
		DetectLabelsResult detectLabelsResult = awsRekognition.detectLabels(detectLabelsRequest);
		
		List<Label> labels = detectLabelsResult.getLabels();
		
		return labels;
		
		//System.err.println("[KeywordsManager - DetectLabels]labels: " + labels);
		
		/*
		for (Label label : labels) {
			
		}
		*/
	}
	
	public void trace() {
		System.err.println("[KeywordsManager - trace] awsRekognition: " + awsRekognition);
	}
	
	/*
	public static void main(String[] args) {
		try {
			String access_key = System.getenv("QQ_AWS_ACCESS_KEY");
			String secret_key = System.getenv("QQ_AWS_SECRET_KEY");
			System.err.println("[KeywordsManager - constructor]access_key: " + access_key);
			System.err.println("[KeywordsManager - constructor]secret_key: " + secret_key);
			if (access_key ==  null || secret_key == null) {
				throw new Exception("Unable to get credentials from env variables");
			}
			AWSCredentials userCredentials = new BasicAWSCredentials(access_key, secret_key);
			AmazonRekognition  awsRekognition = AmazonRekognitionClientBuilder
			        .standard()
			        .withCredentials(new AWSStaticCredentialsProvider(userCredentials))
			        .withRegion("eu-west-1")
			        .build();

			
			KeywordsManager keywordsManager = new KeywordsManager(awsRekognition);
			List<Label> labels = keywordsManager.getKeywords();
			System.err.println("[KeywordsManager - main]labels: " + labels);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/

}

