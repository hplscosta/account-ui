package com.demo.account.ui.config;

import com.demo.account.ui.AccountUiApplicationProperties;
import com.demo.account.ui.controller.account.AccountService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Prepare custom application services in Spring context.<br>
 *
 * @author Hugo Costa
 * @since 1.0.0
 */
@Configuration
public class ServiceConfig {

	@Bean
	public AccountService accountService( AccountUiApplicationProperties properties, RedisTemplate redisTemplate ) {
		return new AccountService( properties.getAccountService().getTopic(), redisTemplate );
	}
}
