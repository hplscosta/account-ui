package com.demo.account.ui.controller.account.client;

import com.demo.account.ui.controller.account.Account;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Sends request to Account Service API
 *
 * @author Hugo Costa
 * @since 1.0.0
 */
@FeignClient( url = "${account-service.url}" )
@RequestMapping( path = "/account", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
public interface AccountServiceClient {

	@PostMapping
	ResponseEntity<Account> create( @RequestBody Account account );
}
