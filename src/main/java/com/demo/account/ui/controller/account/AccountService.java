package com.demo.account.ui.controller.account;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * Account service to validate accounts, sending them to redis server. <br>
 * Communicates through {@link RedisTemplate}.
 *
 * @author Hugo Costa
 * @since 1.0.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {

	private final String redisChannel;

	private final RedisTemplate redisTemplate;

	/**
	 * Validates mandatory properties and send account to redis server.
	 *
	 * @param account {@link Account}
	 */
	public void insert( Account account ) {
		Assert.hasText( account.getUser(), "User cannot be empty." );
		Assert.hasText( account.getName(), "Name cannot be empty." );
		log.info( "Sending user {} with name {} to redis", account.getUser(), account.getName() );
		redisTemplate.convertAndSend( redisChannel, account );
	}
}
