package com.demo.account.ui.controller.account.receiver.web;

import com.demo.account.ui.controller.account.Account;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.messaging.WebSocketStompClient;

/**
 * Service to send new accounts registered in system. <br>
 * Sends account data through {@link WebSocketStompClient} to browser clients who are connected to the topic. <br>
 * Initializes session on first account create.
 *
 * @author Hugo Costa
 * @since 1.0.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AccountStompService {

	private static final String LOCALHOST = "ws://127.0.0.1";

	private static final String DESTINATION = "/accounts";

	private static final String ENDPOINT = "/account-ws-endpoint";

	private final WebSocketStompClient stompClient;

	private final Integer port;

	private StompSession session;

	public void send( Account account ) {
		log.info( "Sending user {} with name {} to browser", account.getUser(), account.getName() );
		if ( session == null || !session.isConnected() ) {
			startSession();
		}
		session.send( DESTINATION, account );
	}

	private void startSession() {
		try {
			session = stompClient.connect( new StringBuilder().append( LOCALHOST ).append( ":" ).append( port ).append( ENDPOINT ).toString(), new AccountStompSessionHandler() ).get();
		}
		catch ( Exception e ) {
			throw new RuntimeException( e );
		}
	}
}
