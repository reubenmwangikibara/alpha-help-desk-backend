package com.alpha.alpha_help_desk_backend;

import io.jsonwebtoken.security.Keys;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.crypto.SecretKey;
import java.util.Base64;

@SpringBootApplication
public class AlphaHelpDeskBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlphaHelpDeskBackendApplication.class, args);

	}

}
