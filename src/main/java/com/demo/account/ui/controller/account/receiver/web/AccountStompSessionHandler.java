package com.demo.account.ui.controller.account.receiver.web;

import com.demo.account.ui.controller.account.Account;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import java.lang.reflect.Type;

/**
 * Extends {@link StompSessionHandlerAdapter}, overriding payload type to {@link Account}.
 *
 * @author Hugo Costa
 * @since 1.0.0
 */
public class AccountStompSessionHandler extends StompSessionHandlerAdapter {

	@Override
	public Type getPayloadType( StompHeaders headers ) {
		return Account.class;
	}
}
