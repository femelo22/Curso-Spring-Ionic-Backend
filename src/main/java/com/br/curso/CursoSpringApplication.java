package com.br.curso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.br.curso.services.S3Service;

@SpringBootApplication
public class CursoSpringApplication implements CommandLineRunner {

	@Autowired
	private S3Service s3;
	
	public static void main(String[] args) {
		SpringApplication.run(CursoSpringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("R O D A N T E   ✔✔✔✔");
		
		s3.uploadFile("C:\\Users\\luiz1\\OneDrive\\Documentos\\imagem\\nois.jpg");
	}

}
