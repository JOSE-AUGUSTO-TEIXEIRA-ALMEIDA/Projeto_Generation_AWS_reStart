package com.jlstudents.crudpostagensusuario.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Generation Brasil CRUD API")
						.description("Sistema de postagens e edição de contéudo por usuário com seu respectivo tema.")
						.contact(new Contact().name("Leila Fernanda da Silva").email("leilafernandadasilva@gmail.com"))
						.version("1.0.0"));
	}
	
}
