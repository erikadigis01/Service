package com.digis01.ECarvajalProgramacionEnCapasService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ECarvajalProgramacionEnCapasServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECarvajalProgramacionEnCapasServiceApplication.class, args);
	}

}
