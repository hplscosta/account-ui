package com.demo.account.ui.account;

import lombok.*;

/**
 * Account entity.
 *
 * @author Hugo Costa
 * @since 1.0.0
 */
@Getter
@EqualsAndHashCode( of = "user" )
@RequiredArgsConstructor( access = AccessLevel.PACKAGE )
public class Account {

	private final String user;

	private final String name;

	@Setter
	private Integer age;

	@Setter
	private Address address;
}
