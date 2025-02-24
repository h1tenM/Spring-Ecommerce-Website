package com.ecommerce.project;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SbEcomApplication {

	public static void main(String[] args){
		Dotenv dotenv = Dotenv.load();
		System.setProperty("MYSQL_PASSWORD", dotenv.get("MYSQL_PASSWORD"));
		SpringApplication.run(SbEcomApplication.class, args);
	}

}
