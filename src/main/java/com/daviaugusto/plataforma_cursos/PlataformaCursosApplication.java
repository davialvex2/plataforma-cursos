package com.daviaugusto.plataforma_cursos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PlataformaCursosApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlataformaCursosApplication.class, args);
	}

}
