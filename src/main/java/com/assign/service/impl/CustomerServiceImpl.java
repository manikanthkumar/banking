package com.assign.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assign.entity.Customer;
import com.assign.exception.RecordNotFoundException;
import com.assign.repository.CustomerRepository;
import com.assign.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository repo;

	@Override
	public Customer createCustomer(Customer customer) {
		return repo.save(customer);
	}

	@Override
	public Customer getCustomerById(Long id) throws RecordNotFoundException {
		Optional<Customer> cust = repo.findById(id);
		if (cust.isPresent()) {
			return cust.get();
		}
		throw new RecordNotFoundException("Customer with the given Id Does not exisis");
	}

}
