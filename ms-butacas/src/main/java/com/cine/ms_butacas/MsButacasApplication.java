package com.cine.ms_butacas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsButacasApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsButacasApplication.class, args);
    }
}
