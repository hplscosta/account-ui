package com.demo.account.ui.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * Web socket configuration.<br>
 * Configure message broker and registry new endpoint to send account data to browser.
 *
 * @author Hugo Costa
 * @since 1.0.0
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

	private static final String ROOT_PATH = "/";

	private static final String APP = "/app";

	private static final String ENDPOINT = "/account-ws-endpoint";

	@Override
	public void configureMessageBroker( MessageBrokerRegistry config ) {
		config.enableSimpleBroker( ROOT_PATH );
		config.setApplicationDestinationPrefixes( APP );
	}

	@Override
	public void registerStompEndpoints( StompEndpointRegistry registry ) {
		registry.addEndpoint( ENDPOINT ).withSockJS();
	}

}
