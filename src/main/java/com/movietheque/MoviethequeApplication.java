package com.movietheque;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com/movietheque/entities")
@EnableJpaRepositories("com.movietheque.repositories")
@ComponentScan(basePackages = {"com.movietheque.controllers", "com.movietheque.services"})
public class MoviethequeApplication {

	public static void main(String[] args) {
 		SpringApplication.run(MoviethequeApplication.class, args);
	}
}
