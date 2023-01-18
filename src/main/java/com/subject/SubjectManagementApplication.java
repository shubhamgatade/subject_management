package com.subject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.subject.*" })
@EntityScan(basePackages = { "com.subject.entity" })
public class SubjectManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(SubjectManagementApplication.class, args);

		System.out.println("Subject Application");
	}
}