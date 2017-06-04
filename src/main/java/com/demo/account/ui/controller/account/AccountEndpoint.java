package com.demo.account.ui.controller.account;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Account endpoint to access {@link Account} data. <br>
 * The {@link RestController} calls {@link AccountService} to insert new accounts.
 *
 * @author Hugo Costa
 * @since 1.0.0
 */
@Slf4j
@RestController
@RequestMapping( path = "/account", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
@RequiredArgsConstructor
public class AccountEndpoint {

	private final AccountService service;

	@PostMapping
	public ResponseEntity<Void> create( @RequestBody Account account ) {
		service.insert( account );
		return ResponseEntity.noContent().build();
	}
}
