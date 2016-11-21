package com.kravchenko.wakeodessa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableAutoConfiguration

@EnableGlobalMethodSecurity(securedEnabled = true)
public class WakeodessaApplication {

	public static void main(String[] args) {
		SpringApplication.run(WakeodessaApplication.class, args);
	}
}
