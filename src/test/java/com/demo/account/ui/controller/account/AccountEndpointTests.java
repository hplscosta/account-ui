package com.demo.account.ui.controller.account;

import com.demo.account.ui.controller.account.exception.AccountEndpointAdvice.ErrorMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Testing {@link AccountEndpoint} methods. <br>
 * Mocks {@link AccountService}. <br>
 * Spring MVC test.
 *
 * @author Hugo Costa
 * @since 1.0.0
 */
@RunWith( SpringRunner.class )
@WebMvcTest( AccountEndpoint.class )
public class AccountEndpointTests {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	@MockBean
	private AccountService service;

	private ObjectMapper mapper;

	@Before
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup( this.context ).build();
		mapper = new ObjectMapper();
	}

	/**
	 * Calling endpoint to create account.<br>
	 * Positive test.
	 */
	@Test
	public void valid_create_account() throws Exception {

		// given
		Account account = new Account( "user", "name" );

		// when
		//@formatter:off
		this.mockMvc.perform( post( "/account" )
													.content( mapper.writeValueAsString( account ) )
													.contentType( MediaType.APPLICATION_JSON ) )
			.andExpect( status().isNoContent() ).andReturn();
		//@formatter:on

		// then
		then( service ).should().insert( account );
	}

	/**
	 * Calling endpoint to save account. Request does not have mandatory fields. Must return an error message.<br>
	 * Negative test.
	 */
	@Test
	public void invalid_save_account_missing_mandatory_fields() throws Exception {

		// given
		Account account = new Account( "user", "name" );
		doThrow( new IllegalArgumentException( "User cannot be empty." ) ).when( service ).insert( account );

		// when
		//@formatter:off
		MvcResult result = this.mockMvc.perform( post( "/account" )
													.content( mapper.writeValueAsString( account ) )
													.contentType( MediaType.APPLICATION_JSON ) )
			.andExpect( status().isBadRequest() ).andReturn();
		//@formatter:on

		// then
		then( service ).should().insert( account );
		ErrorMessage errorMessage = mapper.readValue( result.getResponse().getContentAsString(), ErrorMessage.class );
		assertThat( errorMessage ).isEqualTo( new ErrorMessage( "User cannot be empty." ) );
	}
}
