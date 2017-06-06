package com.demo.account.ui.controller.account.receiver;

import com.demo.account.ui.controller.account.Account;
import com.demo.account.ui.controller.account.receiver.api.AccountServiceClient;
import com.demo.account.ui.controller.account.receiver.web.AccountStompService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.*;

/**
 * Testing {@link AccountReceiver} methods. <br>
 * Mocks {@link AccountServiceClient}. <br>
 * Mocks {@link AccountStompService}. <br>
 *
 * @author Hugo Costa
 * @since 1.0.0
 */
@RunWith( BlockJUnit4ClassRunner.class )
public class AccountReceiverTests {

	private AccountServiceClient accountServiceClient;

	private AccountStompService accountStompService;

	private AccountReceiver receiver;

	@Before
	public void setUp() throws Exception {
		accountServiceClient = mock( AccountServiceClient.class );
		accountStompService = mock( AccountStompService.class );
		receiver = new AccountReceiver( accountServiceClient, accountStompService );
	}

	/**
	 * Create account in account service API and sends message through stomp client.<br>
	 * Positive test.
	 */
	@Test
	public void valid_message_received() throws Exception {

		// given
		Account account = new Account( "user", "name", 30, null );

		// when
		receiver.receive( account );

		//then
		then( accountServiceClient ).should().create( account );
		then( accountStompService ).should().send( account );
	}

	/**
	 * Ignores message when service API fails.<br>
	 * Negative test.
	 */
	@Test
	public void invalid_ignores_message_when_service_api_fails() throws Exception {

		// given
		Account account = new Account( "user", "name", 30, null );

		when( accountServiceClient.create( account ) ).thenThrow( new RuntimeException( "error" ) );

		// when
		receiver.receive( account );

		//then
		then( accountServiceClient ).should().create( account );
		then( accountStompService ).should( times( 0 ) ).send( account );
	}

	/**
	 * Ignores message when service stomp fails.<br>
	 * Negative test.
	 */
	@Test
	public void invalid_ignores_message_when_service_stomp_fails() throws Exception {

		// given
		Account account = new Account( "user", "name", 30, null );

		willThrow( new RuntimeException( "error" ) ).given( accountStompService ).send( account );

		// when
		receiver.receive( account );

		//then
		then( accountServiceClient ).should().create( account );
		then( accountStompService ).should().send( account );
	}
}
