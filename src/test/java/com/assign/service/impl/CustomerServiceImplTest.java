package com.assign.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.assign.entity.Customer;
import com.assign.entity.ServiceType;
import com.assign.exception.RecordNotFoundException;
import com.assign.repository.CustomerRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerServiceImplTest {

	@InjectMocks
	private CustomerServiceImpl service;

	@Mock
	private CustomerRepository repo;

	@Test
	public void testCreateCustomer() {
		Customer cust = new Customer(1L, "name", 8698698L, "address", ServiceType.PRIORITY);
		when(repo.save(cust)).thenReturn(cust);
		assertEquals(cust, service.createCustomer(cust));
	}

	@Test
	public void testGetCustomerById() throws Exception {
		Customer cust = new Customer(1L, "name", 8698698L, "address", ServiceType.PRIORITY);
		Optional<Customer> customer = Optional.of(cust);
		when(repo.findById(1L)).thenReturn(customer);
		assertEquals(cust, service.getCustomerById(1L));
	}

	@Test(expected = RecordNotFoundException.class)
	public void testGetCustomerByIdException() throws Exception {
		when(repo.findById(1L)).thenReturn(Optional.empty());
		service.getCustomerById(1L);
	}
}
