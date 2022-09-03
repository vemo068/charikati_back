package com.example.charikatiback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.example.charikatiback.entity")
@SpringBootApplication
public class CharikatiBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(CharikatiBackApplication.class, args);
	}

}
