package com.project.Bookmyshow;

import com.project.Bookmyshow.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookMyShowApplication {
    @Autowired
	UserRepository userRepository;
	public static void main(String[] args) {
		SpringApplication.run(BookMyShowApplication.class, args);

	}

}
