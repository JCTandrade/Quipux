package com.prueba_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.prueba_backend.domain")
@EnableJpaRepositories("com.prueba_backend.repositories")
public class PruebaBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(PruebaBackendApplication.class, args);
	}

}