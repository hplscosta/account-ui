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

	private final String destination;

	private final WebSocketStompClient stompClient;

	private final String url;

	private StompSession session;

	public void send( Account account ) {
		log.info( "Sending user {} with name {} to browser", account.getUser(), account.getName() );
		if ( session == null || !session.isConnected() ) {
			startSession();
		}
		session.send( destination, account );
	}

	private void startSession() {
		try {
			session = stompClient.connect( url, new AccountStompSessionHandler() ).get();
		}
		catch ( Exception e ) {
			throw new RuntimeException( e );
		}
	}
}
