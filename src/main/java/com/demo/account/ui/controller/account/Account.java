package com.demo.account.ui.controller.account;

import lombok.*;

import java.io.Serializable;

/**
 * Account entity.
 *
 * @author Hugo Costa
 * @since 1.0.0
 */
@Getter
@EqualsAndHashCode( of = "user" )
@RequiredArgsConstructor( access = AccessLevel.PACKAGE )
public class Account implements Serializable {

	private static final long serialVersionUID = 779517484250084699L;

	private final String user;

	private final String name;

	@Setter
	private Integer age;

	@Setter
	private Address address;
}
