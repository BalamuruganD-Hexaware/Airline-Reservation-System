package com.bala.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
@SpringBootApplication
public class ArsBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArsBackendApplication.class, args);
	}

}
