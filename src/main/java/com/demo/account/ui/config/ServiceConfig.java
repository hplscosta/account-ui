package com.demo.account.ui.config;

import com.demo.account.ui.AccountUiApplicationProperties;
import com.demo.account.ui.controller.account.AccountService;
import com.demo.account.ui.controller.account.receiver.AccountReceiver;
import com.demo.account.ui.controller.account.receiver.api.AccountServiceClient;
import com.demo.account.ui.controller.account.receiver.web.AccountStompService;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

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

	@Bean
	public AccountReceiver accountReceiver( AccountServiceClient accountServiceClient, AccountStompService accountStompService ) {
		return new AccountReceiver( accountServiceClient, accountStompService );
	}

	@Bean
	public AccountStompService accountStompService( ServerProperties properties ) throws ExecutionException, InterruptedException {

		WebSocketClient simpleWebSocketClient = new StandardWebSocketClient();
		List<Transport> webSocketTransports = Arrays.asList( new WebSocketTransport( simpleWebSocketClient ) );

		SockJsClient sockJsClient = new SockJsClient( webSocketTransports );
		WebSocketStompClient stompClient = new WebSocketStompClient( sockJsClient );
		stompClient.setMessageConverter( new MappingJackson2MessageConverter() );

		return new AccountStompService( stompClient, properties.getPort() );
	}
}
