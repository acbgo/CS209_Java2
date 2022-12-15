package com.example.Lab13SpringDemo;

import com.example.Lab13SpringDemo.service.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Lab13SpringDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(Lab13SpringDemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentService service){
		return args -> {
			service.addStudents();
		};
	}
}
