package com.mouseheroes.educatoon;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class EducatoonApplication implements CommandLineRunner {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run( EducatoonApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		try {
			String sql = "SELECT * FROM students";
			Object classe = this.jdbcTemplate.queryForObject(sql, Object.class);

			if (classe != null) {
				JSONPObject gon = new JSONPObject(classe.toString(), Object.class);
				System.out.println("valor retornado: " + gon.toString());
			}
		}catch(java.lang.Exception error){
			System.out.println(error);
		}
	}
}
