package com.ecommerce.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableConfigServer
public class ConfigserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigserverApplication.class, args);
	}

}
