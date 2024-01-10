package com.sytoss.ecommerce.platform.fulfillmentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FulfillmentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FulfillmentServiceApplication.class, args);
	}

}
