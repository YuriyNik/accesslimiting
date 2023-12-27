package com.assigment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AccesslimitingApplication {
	public static void main(String[] args) {
		SpringApplication.run(AccesslimitingApplication.class, args);
	}

}
