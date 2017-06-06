package com.demo.account.ui.controller.account.receiver;

import com.demo.account.ui.controller.account.Account;
import com.demo.account.ui.controller.account.receiver.api.AccountServiceClient;
import com.demo.account.ui.controller.account.receiver.web.AccountStompService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Handles message fetched from redis channel. <br>
 * Calls {@link AccountServiceClient} to persist account data.<br>
 * Sends {@link Account} data to client browsers through {@link AccountStompService}.
 *
 * @author Hugo Costa
 * @since 1.0.0
 */
@Slf4j
@RequiredArgsConstructor
public class AccountReceiver {

	private final AccountServiceClient accountServiceClient;

	private final AccountStompService accountStompService;

	public void receive( Account account ) {
		log.info( "Account user {} received!", account.getUser() );
		try {
			accountServiceClient.create( account );
			accountStompService.send( account );
		} catch ( Exception e ) {
			log.error( "Error processing message from redis", e );
		}
	}
}
