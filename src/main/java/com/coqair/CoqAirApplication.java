package com.coqair;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
//@ComponentScan(basePackages = "com.coqair")
//@Profile(value= {"classpath:application-config-dev.properties"})
public class CoqAirApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoqAirApplication.class, args);
	}

}
