package com.demo.account.ui;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Application properties.
 *
 * @author Hugo Costa
 * @since 1.0.0
 */
@Data
@ConfigurationProperties( prefix = "demo.ui" )
public class AccountUiApplicationProperties {

	private AccountServiceProperties accountService = new AccountServiceProperties();

	@Data
	public static class AccountServiceProperties {

		private String url;

		private String topic = "accounts";
	}
}
