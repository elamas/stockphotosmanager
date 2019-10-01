package stockphotosmanager.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import stockphotosmanager.models.Photo;
import stockphotosmanager.services.PhotoService;


@RestController
public class PhotoController {

	@Autowired
	private PhotoService photoService;

	
    @RequestMapping(value = "/backend/photos", method = RequestMethod.GET)
    public List<Photo> findAll() {
    	return photoService.findAll();
    }
}
