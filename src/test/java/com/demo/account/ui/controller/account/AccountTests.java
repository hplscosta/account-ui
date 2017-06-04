package com.demo.account.ui.controller.account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Testing {@link Account} entity object. <br>
 * This test has {@link Address} value object dependency.
 *
 * @author Hugo Costa
 * @since 1.0.0
 */
@RunWith( BlockJUnit4ClassRunner.class )
public class AccountTests {

	/**
	 * Create an account successfully <br>
	 * Positive test.
	 */
	@Test
	public void valid_account() {

		// given
		Account account = new Account( "user", "name", 10, new Address( "city", "country" ) );

		// asserts
		assertThat( account.getUser() ).isEqualTo( "user" );
		assertThat( account.getName() ).isEqualTo( "name" );
		assertThat( account.getAge() ).isEqualTo( 10 );
		assertThat( account.getAddress() ).isNotNull();
		assertThat( account.getAddress().getCity() ).isEqualTo( "city" );
		assertThat( account.getAddress().getCountry() ).isEqualTo( "country" );
	}

	/**
	 * Create an account successfully, only with mandatory fields <br>
	 * Positive test.
	 */
	@Test
	public void valid_account_mandatory_properties() {

		// given
		Account account = new Account( "user", "name", null, null );

		// asserts
		assertThat( account.getUser() ).isEqualTo( "user" );
		assertThat( account.getName() ).isEqualTo( "name" );
	}
}
