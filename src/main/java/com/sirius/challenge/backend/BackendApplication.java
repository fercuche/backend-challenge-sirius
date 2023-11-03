package com.sirius.challenge.backend;

import com.sirius.challenge.backend.security.models.Role;
import com.sirius.challenge.backend.security.models.User;
import com.sirius.challenge.backend.security.repositories.UserRepository;
import com.sirius.challenge.backend.services.impl.BrevoEmailService;
import com.sirius.challenge.backend.services.impl.MailgunEmailService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
		title = "Email Service",
		version = "V1.0",
		description = "Challenge project for Sirius Software")
)
@SecurityScheme(
		name = "Bearer Authentication",
		type = SecuritySchemeType.HTTP,
		bearerFormat = "JWT",
		scheme = "bearer"
)
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(UserRepository userRepository, PasswordEncoder passwordEncoder) {

		return (args -> {

			User user1 = new User(
					"Fernando", "Maciel","fer2.maciel@gmail.com",
					passwordEncoder.encode("asd1234"), Role.ADMIN);
			user1.setEmailDailyCount(10);

			User user2 = new User(
					"Melba", "Morel","melbam@mail.com",
					passwordEncoder.encode("asd1234"), Role.USER);
			user2.setEmailDailyCount(1000);


			userRepository.save(user1);
			userRepository.save(user2);


		});
	}

}
