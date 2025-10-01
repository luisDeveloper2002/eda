package com.example.gatewat_zull;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class GatewatZullApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewatZullApplication.class, args);
	}

}
