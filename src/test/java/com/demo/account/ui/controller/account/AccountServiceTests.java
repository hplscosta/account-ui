package com.demo.account.ui.controller.account;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.springframework.data.redis.core.RedisTemplate;

import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

/**
 * Testing {@link AccountService} methods. <br>
 * Mocks {@link RedisTemplate}.
 *
 * @author Hugo Costa
 * @since 1.0.0
 */
@RunWith( BlockJUnit4ClassRunner.class )
public class AccountServiceTests {

	private AccountService service;

	private String redisChannel;

	private RedisTemplate redisTemplate;

	@Before
	public void setUp() throws Exception {
		redisTemplate = mock( RedisTemplate.class );
		redisChannel = "dummy";
		service = new AccountService( redisChannel, redisTemplate );
	}

	/**
	 * Calling service to insert a valid account.<br>
	 * Positive test.
	 */
	@Test
	public void valid_insert_new_account() {

		// given
		Account account = new Account( "user", "name", 10, new Address( "city", "country" ) );

		// when
		service.insert( account );

		// then
		then( redisTemplate ).should().convertAndSend( redisChannel, account );
	}

	/**
	 * Calling service to insert an invalid account. Missing user.<br>
	 * Negative test.
	 */
	@Test( expected = IllegalArgumentException.class )
	public void invalid_insert_account_missing_user() {

		// given
		Account account = new Account( null, "name", 10, new Address( "city", "country" ) );

		// when
		service.insert( account );
	}

	/**
	 * Calling service to insert an invalid account. Missing name.<br>
	 * Negative test.
	 */
	@Test( expected = IllegalArgumentException.class )
	public void invalid_insert_account_missing_name() {

		// given
		Account account = new Account( "user", null, 10, new Address( "city", "country" ) );

		// when
		service.insert( account );
	}
}
