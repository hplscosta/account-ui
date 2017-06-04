package com.demo.account.ui.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Redis configuration.<br>
 * Creates {@link RedisTemplate} to send message to redis server.<br>
 *
 * @author Hugo Costa
 * @since 1.0.0
 */
@Configuration
public class RedisConfig {

	@Bean
	public RedisTemplate redisTemplate( RedisConnectionFactory factory ) {
		RedisTemplate redisTemplate = new RedisTemplate();
		redisTemplate.setConnectionFactory( factory );
		return redisTemplate;
	}
}
