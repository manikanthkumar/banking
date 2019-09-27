package com.assign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assign.entity.Customer;
import com.assign.exception.RecordNotFoundException;
import com.assign.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService service;

	@PostMapping("/")
	public Customer saveCustomer(@RequestBody Customer customer) {
		return service.createCustomer(customer);
	}

	@GetMapping("/{customerId}")
	public Customer getCustomerById(@PathVariable Long customerId) throws RecordNotFoundException {
		return service.getCustomerById(customerId);
	}
}
