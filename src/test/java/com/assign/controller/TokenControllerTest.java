package com.assign.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.assign.entity.CounterServiceType;
import com.assign.entity.Customer;
import com.assign.entity.ServiceType;
import com.assign.entity.Token;
import com.assign.entity.TokenStatus;
import com.assign.service.CustomerService;
import com.assign.service.TokenService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
public class TokenControllerTest {

	@InjectMocks
	private TokenController controller;

	@Mock
	private TokenService service;

	@Mock
	private CustomerService custService;

	private MockMvc mockMvc;
	Token token;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		token = new Token(1L, TokenStatus.active, "comments", "actionItem", 1L, ServiceType.PRIORITY,
				CounterServiceType.deposit);
	}

	@Test
	public void testUpdateTokenStatus() throws Exception {
		doNothing().when(service).updateTokenStatus(TokenStatus.completed, 1L);

		MvcResult result = mockMvc.perform(post("/tokens/updateTokenStatus/1")
				.param("tokenStatus", TokenStatus.completed.toString()).contentType(MediaType.APPLICATION_JSON_UTF8))
				.andReturn();

		String resultString = result.getResponse().getContentAsString();

		assertEquals("Updated token status Successfully", resultString);
	}

	@Test
	public void testIssueTokenToCustomer() throws Exception {
		when(service.issueToken(any(Customer.class), any(CounterServiceType.class))).thenReturn(1L);
		when(custService.getCustomerById(1L)).thenReturn(new Customer());

		MvcResult result = mockMvc
				.perform(get("/tokens/issueToken/1/deposit").contentType(MediaType.APPLICATION_JSON_UTF8)).andReturn();

		String tokenId = result.getResponse().getContentAsString();
		Long actualTokenId = 1L;
		assertEquals(tokenId, actualTokenId.toString());

	}

	@Test
	public void testCreateCustomerAndIssueToken() throws Exception {
		when(service.issueToken(any(Customer.class), any(CounterServiceType.class))).thenReturn(1L);
		when(custService.createCustomer(any(Customer.class))).thenReturn(new Customer());

		byte[] byteData = new ObjectMapper().writeValueAsBytes(new Customer());
		MvcResult result = mockMvc.perform(post("/tokens/issueToken/newCustomer/deposit")
				.contentType(MediaType.APPLICATION_JSON_UTF8).content(byteData)).andReturn();

		String tokenId = result.getResponse().getContentAsString();
		Long actualTokenId = 1L;
		assertEquals(tokenId, actualTokenId.toString());
	}

	@Test
	public void testAssignCustomerToCounterServiceType() throws Exception {
		when(service.changeTokenCounter(1L, CounterServiceType.deposit)).thenReturn(1L);

		MvcResult result = mockMvc
				.perform(get("/tokens/changeTokenCounter/1/deposit").contentType(MediaType.APPLICATION_JSON_UTF8))
				.andReturn();

		String tokenId = result.getResponse().getContentAsString();
		Long actualTokenId = 1L;
		assertEquals(tokenId, actualTokenId.toString());
	}

}
