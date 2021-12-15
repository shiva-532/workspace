package com.bvr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DatabaseServiceApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(DatabaseServiceApplication.class, args);
	}

	private static final Logger log = LoggerFactory.getLogger(DatabaseServiceApplication.class);
	
	@Autowired
	private BookRepository repository;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		log.info("Start DB Application");
		
		repository.save(new Book("MicroServices"));
		repository.save(new Book("Python"));
		repository.save(new Book("GoLang"));
		repository.save(new Book("Javascript"));
		repository.save(new Book("Spark"));
		repository.save(new Book("AutoML"));
		repository.save(new Book("Java"));
		
		
		System.out.println("\n find all records0");
		
		repository.findAll().forEach(x -> System.out.println(x));
		
		System.out.println("\n find by id");
		
		repository.findById(3l).ifPresent(x -> System.out.println(x));
		
		System.out.println("\n find Ny Name records0");
		
		repository.findByName("Java").forEach(x -> System.out.println(x));
		
	}
	
}
