package com.gugafood.gugafood;

import com.gugafood.gugafood.infrastructure.repository.CustomJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class GugafoodApplication {

	public static void main(String[] args) {

		System.out.println();
		SpringApplication.run(GugafoodApplication.class, args);
	}

}
