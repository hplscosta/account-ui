package com.demo.account.ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties( AccountUiApplicationProperties.class )
public class AccountUiApplication {

	public static void main( String[] args ) {
		SpringApplication.run( AccountUiApplication.class, args );
	}
}
