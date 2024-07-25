package com.example.ContactManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ContactManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContactManagerApplication.class, args);
	}

}
