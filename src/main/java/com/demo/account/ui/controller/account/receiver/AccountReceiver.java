package com.demo.account.ui.controller.account.receiver;

/**
 * Created by hc on 04-06-2017.
 */

import com.demo.account.ui.controller.account.Account;
import com.demo.account.ui.controller.account.client.AccountServiceClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Handles message fetched from redis channel. <br>
 * Calls {@link AccountServiceClient} to persist account data.<br>
 * TODO calls XXX websocket
 *
 * @author Hugo Costa
 * @since 1.0.0
 */
@Slf4j
@RequiredArgsConstructor
public class AccountReceiver {

	private final AccountServiceClient client;

	public void receive( Account account ) {
		log.info( "Account user {} received!", account.getUser() );
		client.create( account );
	}
}
