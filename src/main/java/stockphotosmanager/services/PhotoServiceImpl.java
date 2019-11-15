package stockphotosmanager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.rekognition.model.Label;

import stockphotosmanager.models.Photo;
import stockphotosmanager.repositories.PhotoRepository;
import stockphotosmanager.util.KeywordsManager;

import java.util.List;

@Service("photoService")
public class PhotoServiceImpl implements PhotoService {
	
	@Autowired
	private PhotoRepository photoRepository;
	
	@Autowired
	private KeywordsManager keywordsManager;

	@Override
	public List<Photo> findAll() {
		System.err.println("[PhotoServiceImpl - findAll]");
		System.err.println("[PhotoServiceImpl - processPhoto]keywordsManager: " + keywordsManager);
		return this.photoRepository.findAll();
	}

	@Override
	public void processPhoto(String bucketName, String keyNormalized) {
		System.err.println("[PhotoServiceImpl - processPhoto]bucketName: " + bucketName);
		System.err.println("[PhotoServiceImpl - processPhoto]keyNormalized: " + keyNormalized);
		System.err.println("[PhotoServiceImpl - processPhoto]keywordsManager: " + keywordsManager);
		//quitar
//		System.err.println("[PhotoServiceImpl - processPhoto]antes del trace");
//		keywordsManager.trace();
//		System.err.println("[PhotoServiceImpl - processPhoto]despues del trace");
		//llama al KeywordManager para obtener las keywords
		 List<Label> labels = keywordsManager.getKeywords();
		//inserta en bbdd la photo
		//TODO
		//genera un thumb
		//TODO
	}
	
	//para tests lambdas
	@Override
	public void setKeywordsManager(KeywordsManager keywordsManager) {
		this.keywordsManager = keywordsManager;
	}
	
}
