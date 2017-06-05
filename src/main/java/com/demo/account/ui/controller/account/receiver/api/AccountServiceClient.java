package com.demo.account.ui.controller.account.receiver.api;

import com.demo.account.ui.controller.account.Account;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Sends request to Account Service API
 *
 * @author Hugo Costa
 * @since 1.0.0
 */
@FeignClient( name = "account", url = "${demo.ui.account-service.url}" )
@RequestMapping( consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
public interface AccountServiceClient {

	@RequestMapping( value = "/account", method = RequestMethod.POST )
	ResponseEntity<Account> create( Account account );
}
