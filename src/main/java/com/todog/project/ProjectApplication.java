package com.todog.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@SpringBootApplication(scanBasePackages = {"com.todog"})
@EnableJpaRepositories("com.todog.project.interfaces")
public class ProjectApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}
	@GetMapping("/hello")
  public ResponseEntity<?> hello(@RequestParam(value = "name", defaultValue = "World") String name) {
    return ResponseEntity.status(HttpStatus.OK).body(String.format("Hello %s!", name));
  }
}
