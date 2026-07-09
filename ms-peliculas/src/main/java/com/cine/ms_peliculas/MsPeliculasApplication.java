package com.cine.ms_peliculas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(excludeName = {
    "org.springdoc.core.configuration.SpringDocHateoasConfiguration",
    "org.springframework.boot.autoconfigure.hateoas.HateoasAutoConfiguration"
})
@EnableDiscoveryClient

public class MsPeliculasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsPeliculasApplication.class, args);
	}

}
