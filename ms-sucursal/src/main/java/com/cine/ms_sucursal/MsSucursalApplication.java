package com.cine.ms_sucursal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsSucursalApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsSucursalApplication.class, args);
	}

}
