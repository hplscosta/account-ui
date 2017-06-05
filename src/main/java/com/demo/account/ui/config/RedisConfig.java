package com.demo.account.ui.config;

import com.demo.account.ui.AccountUiApplicationProperties;
import com.demo.account.ui.controller.account.Account;
import com.demo.account.ui.controller.account.receiver.AccountReceiver;
import org.apache.commons.lang.SerializationUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * Redis configuration.<br>
 * Creates {@link RedisTemplate} to send message to redis server.<br>
 * Instantiate {@link MessageListenerAdapter} to handle messages from redis server. Handles messages with {@link AccountReceiver}.
 *
 * @author Hugo Costa
 * @since 1.0.0
 */
@Configuration
public class RedisConfig {

	private static final String RECEIVER_METHOD = "receive";

	@Bean
	public RedisTemplate redisTemplate( RedisConnectionFactory factory ) {

		RedisSerializer<Account> serializer = accountRedisSerializer();

		RedisTemplate<String, Account> redisTemplate = new RedisTemplate();
		redisTemplate.setConnectionFactory( factory );
		redisTemplate.setDefaultSerializer( serializer );
		return redisTemplate;
	}

	@Bean
	public MessageListenerAdapter messageListenerAdapter( AccountReceiver accountReceiver ) {
		MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter( accountReceiver, RECEIVER_METHOD );
		messageListenerAdapter.setSerializer( accountRedisSerializer() );
		return messageListenerAdapter;
	}

	@Bean
	public RedisMessageListenerContainer container( RedisConnectionFactory factory, MessageListenerAdapter listener, AccountUiApplicationProperties properties ) {
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory( factory );
		container.addMessageListener( listener, new ChannelTopic( properties.getAccountService().getTopic() ) );
		return container;
	}

	private RedisSerializer<Account> accountRedisSerializer() {
		return new RedisSerializer<Account>() {

			@Override
			public byte[] serialize( Account account ) throws SerializationException {
				return SerializationUtils.serialize( account );
			}

			@Override
			public Account deserialize( byte[] bytes ) throws SerializationException {
				return (Account) SerializationUtils.deserialize( bytes );
			}
		};
	}
}
