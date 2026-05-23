package com.supermercado.supermercado;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class SupermercadoApplication {

	@PostConstruct
    public void init() {
        // Fuerza a la aplicación a usar la zona horaria de Colombia
        TimeZone.setDefault(TimeZone.getTimeZone("America/Bogota"));
    }

	
	public static void main(String[] args) {
		SpringApplication.run(SupermercadoApplication.class, args);
	}

}
