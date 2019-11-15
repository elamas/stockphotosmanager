package stockphotosmanager.services;

import java.util.List;

import stockphotosmanager.models.Photo;
import stockphotosmanager.util.KeywordsManager;

public interface PhotoService {

	public List<Photo> findAll();
	public void processPhoto(String bucketName, String keyNormalized);
	public void setKeywordsManager(KeywordsManager keywordsManager);
}
