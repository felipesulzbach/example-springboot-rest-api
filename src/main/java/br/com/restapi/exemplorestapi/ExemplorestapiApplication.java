package br.com.restapi.exemplorestapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ExemplorestapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExemplorestapiApplication.class, args);
    }
}
