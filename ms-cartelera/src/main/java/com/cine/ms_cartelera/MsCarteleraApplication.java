package com.cine.ms_cartelera;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsCarteleraApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsCarteleraApplication.class, args);
	}

}
