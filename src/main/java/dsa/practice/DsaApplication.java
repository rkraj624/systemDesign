package dsa.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DsaApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(DsaApplication.class, args);
		System.out.println("Application Id: " + run.getId());
//		run.close();
	}

}
