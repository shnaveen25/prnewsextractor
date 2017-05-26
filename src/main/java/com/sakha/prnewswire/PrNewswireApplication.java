package com.sakha.prnewswire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PrNewswireApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrNewswireApplication.class, args);
	}
}
