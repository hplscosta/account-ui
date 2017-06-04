package com.demo.account.ui.controller.account;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * Address value object
 *
 * @author Hugo Costa
 * @since 1.0.0
 */
@Getter
@RequiredArgsConstructor( access = AccessLevel.PACKAGE )
public class Address implements Serializable {

	private static final long serialVersionUID = -7371445559560682825L;

	private final String city;

	private final String country;
}
