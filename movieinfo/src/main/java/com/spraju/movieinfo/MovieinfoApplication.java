package com.spraju.movieinfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
public class MovieinfoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieinfoApplication.class, args);
	}

}
