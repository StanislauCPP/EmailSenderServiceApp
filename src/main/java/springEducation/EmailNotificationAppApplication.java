package springEducation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EmailNotificationAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(EmailNotificationAppApplication.class, args);
	}
}
