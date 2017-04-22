package controllers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("entities")
@EnableJpaRepositories("repositories")
@ComponentScan(basePackages = {"controllers","services"})
public class MoviethequeApplication {

	public static void main(String[] args) {
 		SpringApplication.run(MoviethequeApplication.class, args);
	}
}
