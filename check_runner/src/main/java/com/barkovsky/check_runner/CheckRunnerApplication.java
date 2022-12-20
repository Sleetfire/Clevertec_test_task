package com.barkovsky.check_runner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.barkovsky.check_runner.repository.db_repository.api")
public class CheckRunnerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CheckRunnerApplication.class, args);
	}

}
