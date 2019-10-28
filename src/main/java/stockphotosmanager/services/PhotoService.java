package stockphotosmanager.services;

import java.util.List;

import stockphotosmanager.models.Photo;

public interface PhotoService {

	public List<Photo> findAll();
	public void processPhoto(String bucketName, String keyNormalized);
}
