package stockphotosmanager.processes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.context.annotation.ComponentScan;

import stockphotosmanager.models.Photo;
import stockphotosmanager.services.PhotoService;

@ComponentScan(basePackages = {"stockphotosmanager"})
public class Process2 implements CommandLineRunner {

	@Autowired
	private PhotoService photoService;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		List<Photo> photos = photoService.findAll();
		System.err.println("[Process2]photos.size(): " + photos.size());
	}
	
    public static void main(String[] args) throws Exception {
    	System.err.println("[Process2] Estoy en el Process2");
    	SpringApplication app = new SpringApplication(Process2.class);
    	app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);
    }

}
