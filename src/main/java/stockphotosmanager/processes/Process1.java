package stockphotosmanager.processes;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;

import stockphotosmanager.util.KeywordsManager;

public class Process1 implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		
		try {
//			KeywordsManager keywordsManager = new KeywordsManager();
//			keywordsManager.getKeywords();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
    public static void main(String[] args) throws Exception {
    	System.err.println("[Process1] Estoy en el Process1");
    	SpringApplication app = new SpringApplication(Process1.class);
    	app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);
    }


}
