package stockphotosmanager.util;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.model.Label;
import com.amazonaws.services.rekognition.model.DetectLabelsRequest;
import com.amazonaws.services.rekognition.model.DetectLabelsResult;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;


@RunWith(MockitoJUnitRunner.class)
public class KeywordsManagerTest {
	
	@Mock
	AmazonRekognition  awsRekognition;
	
	@Test
	public void getKeywords() throws Exception {
		//awsRekognition.detectLabels(detectLabelsRequest);
		
		//mock begin
		List<Label> labels = new ArrayList<Label>();
		labels.add(new Label().withName("lalala"));
		DetectLabelsResult detectLabelsResult = new DetectLabelsResult().withLabels(labels); 
		when(awsRekognition.detectLabels(any(DetectLabelsRequest.class))).thenReturn(detectLabelsResult);
		//mock end
		
		KeywordsManager keywordsManager = new KeywordsManager(awsRekognition);
		List<Label> labelsReturned = keywordsManager.getKeywords();
		//System.err.println("[KeywordsManagerTest - main]labelsReturned: " + labelsReturned);
		assertEquals(labels, labelsReturned);
	}

}
