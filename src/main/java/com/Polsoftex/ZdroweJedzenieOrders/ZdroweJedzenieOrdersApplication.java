package com.Polsoftex.ZdroweJedzenieOrders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ZdroweJedzenieOrdersApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZdroweJedzenieOrdersApplication.class, args);
    }

}
