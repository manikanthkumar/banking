package com.assign.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.assign.entity.Counter;
import com.assign.entity.CounterServiceType;
import com.assign.entity.CounterTokenMap;
import com.assign.entity.Customer;
import com.assign.entity.ServiceType;
import com.assign.entity.Token;
import com.assign.entity.TokenStatus;
import com.assign.exception.RecordNotFoundException;
import com.assign.repository.TokenRepository;
import com.assign.service.CounterService;
import com.assign.service.CounterTokenMapService;
import com.assign.service.CustomerService;

@RunWith(SpringJUnit4ClassRunner.class)
public class TokenServiceImplTest {

	@InjectMocks
	private TokenServiceImpl service;

	@Mock
	private CounterService counterService;

	@Mock
	private CustomerService customerService;

	@Mock
	private TokenRepository repo;

	@Mock
	private CounterTokenMapService counterTokenMapService;

	List<Counter> counters = new ArrayList<>();
	List<CounterTokenMap> maps = new ArrayList<>();
	List<Customer> customers = new ArrayList<>();
	Token token;

	@Before
	public void setUp() throws Exception {
		token = new Token(1L, TokenStatus.active, "comments", "actionItem", 1L, ServiceType.PRIORITY,
				CounterServiceType.deposit);
		counters.add(new Counter(1L, "first", 1L, ServiceType.PRIORITY, CounterServiceType.deposit));
		counters.add(new Counter(2L, "second", 2L, ServiceType.REGULAR, CounterServiceType.enquiry));
		customers.add(new Customer(1L, "name", 8698698L, "address", ServiceType.PRIORITY));
		customers.add(new Customer(2L, "name1", 8698698L, "address", ServiceType.REGULAR));
		maps.add(new CounterTokenMap(1L, 1L));
		maps.add(new CounterTokenMap(2L, 1L));
		maps.add(new CounterTokenMap(3L, 1L));
		maps.add(new CounterTokenMap(4L, 2L));
		maps.add(new CounterTokenMap(5L, 2L));
	}

	@Test
	public void testCreateToken() {
		when(repo.save(any(Token.class))).thenReturn(token);
		assertEquals(token, service.createToken(1L, ServiceType.PRIORITY));
	}

	@Test
	public void testUpdateToken() {
		when(repo.save(token)).thenReturn(token);
		assertEquals(token, service.updateToken(token));
	}

	@Test
	public void testUpdateTokenStatus() throws Exception {
		when(counterTokenMapService.getCounterIdFromTokenId(1L)).thenReturn(1L);
		List<CounterTokenMap> selectedToken = new ArrayList<>();
		selectedToken.add(maps.get(0));
		selectedToken.add(maps.get(1));
		selectedToken.add(maps.get(2));
		when(counterTokenMapService.getByCounterId(1L)).thenReturn(maps);
		when(counterService.getCounterById(1L)).thenReturn(counters.get(0));
		when(counterService.updateCounter(counters.get(0))).thenReturn(counters.get(0));
		Optional<Token> token1 = Optional.of(token);
		when(repo.findById(1L)).thenReturn(token1);
		service.updateTokenStatus(TokenStatus.completed, 1L);
	}

	@Test
	public void testGetTokenById() throws Exception {
		Optional<Token> token1 = Optional.of(token);
		when(repo.findById(1L)).thenReturn(token1);
		assertEquals(token, service.getTokenById(1L));
	}

	@Test(expected = RecordNotFoundException.class)
	public void testGetTokenByIdException() throws Exception {
		when(repo.findById(1L)).thenReturn(Optional.empty());
		service.getTokenById(1L);
	}

	@Test
	public void testIssueToken() {
		when(repo.save(any(Token.class))).thenReturn(token);
		List<Counter> counter = new ArrayList<>();
		counter.add(counters.get(0));
		when(counterService.getCounterByServiceAndCounterServiceType(any(ServiceType.class),
				any(CounterServiceType.class))).thenReturn(counter);
		when(counterTokenMapService.getAll()).thenReturn(maps);
		when(counterTokenMapService.createMap(maps.get(0))).thenReturn(maps.get(0));
		assertEquals(token.getTokenId().toString(),
				service.issueToken(customers.get(0), CounterServiceType.deposit).toString());
	}

	@Test
	public void testChangeTokenCounter() throws Exception {
		when(counterTokenMapService.deleteMapByTokenId(1L)).thenReturn("Deleted Successfully");
		when(counterTokenMapService.getCounterIdFromTokenId(1L)).thenReturn(1L);
		when(counterTokenMapService.getByCounterId(1L)).thenReturn(maps);
		when(counterService.updateCounter(any(Counter.class))).thenReturn(counters.get(0));
		when(customerService.getCustomerById(1L)).thenReturn(customers.get(0));
		when(counterService.getCounterById(1L)).thenReturn(counters.get(0));
		Optional<Token> token1 = Optional.of(token);
		when(repo.findById(1L)).thenReturn(token1);
		List<Counter> counter = new ArrayList<>();
		counter.add(counters.get(0));
		when(counterService.getCounterByServiceAndCounterServiceType(any(ServiceType.class),
				any(CounterServiceType.class))).thenReturn(counter);
		when(counterTokenMapService.getAll()).thenReturn(maps);
		when(counterTokenMapService.createMap(maps.get(0))).thenReturn(maps.get(0));
		assertEquals(token.getTokenId().toString(),
				service.changeTokenCounter(1L, CounterServiceType.deposit).toString());
	}

}
