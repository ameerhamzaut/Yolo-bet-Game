package yolo.bet.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import yolo.bet.project.model.RandomNumberGenerator;

@SpringBootApplication
public class ProjectApplication {

	@Bean
	public RandomNumberGenerator randomNumberGenerator() {
		return new RandomNumberGenerator();
	}

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

}
