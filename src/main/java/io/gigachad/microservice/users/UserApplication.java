package io.gigachad.microservice.users;

import io.gigachad.microservice.users.services.UserPanService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserApplication {

	public static void main(String[] args){
		SpringApplication.run(UserApplication.class, args);
	}
}
