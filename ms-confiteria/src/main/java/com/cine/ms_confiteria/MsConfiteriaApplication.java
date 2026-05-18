package com.cine.ms_confiteria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsConfiteriaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsConfiteriaApplication.class, args);
	}

}
