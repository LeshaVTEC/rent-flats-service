package org.alexey.rentflatsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RentFlatsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentFlatsServiceApplication.class, args);
	}

}
