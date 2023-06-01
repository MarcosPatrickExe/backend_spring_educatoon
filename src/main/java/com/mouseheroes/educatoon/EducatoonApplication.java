package com.mouseheroes.educatoon;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class EducatoonApplication implements CommandLineRunner {

	private JdbcTemplate jdbcTemplate;


	public static void main(String[] args) {
		SpringApplication.run( EducatoonApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		String sql = "SELECT * FROM students";
		int returrn = this.jdbcTemplate.update(sql);

		System.out.println("valor retornado: "+returrn);
	}
}
