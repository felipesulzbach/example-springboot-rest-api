package br.com.restapi.exemplorestapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

import br.com.restapi.exemplorestapi.repository.UsuarioRepository;

@SpringBootApplication
public class ExemplorestapiApplication {

	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		source.setBasenames("messages/messages");
		source.setUseCodeAsDefaultMessage(true);
		return source;
	}

	public static void main(String[] args) {
		SpringApplication.run(UsuarioRepository.class, args);
	}
}
