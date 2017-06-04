package com.demo.account.ui.controller.account;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Account entity.
 *
 * @author Hugo Costa
 * @since 1.0.0
 */
@Getter
@EqualsAndHashCode( of = "user" )
public class Account implements Serializable {

	private static final long serialVersionUID = 779517484250084699L;

	private final String user;

	private final String name;

	@Setter
	private Integer age;

	@Setter
	private Address address;

	//@formatter:off
	@JsonCreator(mode = JsonCreator.Mode.DEFAULT)
	public Account(
		@JsonProperty("user") String user,
		@JsonProperty("name") String name,
		@JsonProperty("age") Integer age,
		@JsonProperty("address") Address address ) {
			this.user = user;
			this.name = name;
			this.age = age;
			this.address = address;
	}
	//@formatter:on
}
