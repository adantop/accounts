package com.accounts.app;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.accounts.app.entities.Role;
import com.accounts.app.repositories.UserService;

@SpringBootApplication
public class AccountsApplication {
	
	@Bean
    public CommandLineRunner setupDefaultUser(UserService service) {
        return args -> {
            service.save(new com.accounts.app.entities.User(
                    "admin",
                    "root",
                    Arrays.asList( new Role("READ"),new Role("WRITE")),
                    true
            ));
        };
    }
	
	@Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}

