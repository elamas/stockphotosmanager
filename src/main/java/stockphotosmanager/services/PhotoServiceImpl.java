package stockphotosmanager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import stockphotosmanager.models.Photo;
import stockphotosmanager.repositories.PhotoRepository;

import java.util.List;

@Service("photoService")
public class PhotoServiceImpl implements PhotoService {
	
	@Autowired
	private PhotoRepository photoRepository;

	@Override
	public List<Photo> findAll() {
		System.err.println("[PhotoServiceImpl - findAll]");
		return this.photoRepository.findAll();
	}

	@Override
	public void processPhoto(String bucketName, String keyNormalized) {
		System.err.println("[PhotoServiceImpl - processPhoto]bucketName: " + bucketName);
		System.err.println("[PhotoServiceImpl - processPhoto]keyNormalized: " + keyNormalized);
		//llama al KeywordManager para obtener las keywords
		//TODO
		//inserta en bbdd la photo
		//TODO
		//genera un thumb
		//TODO
	}

}
