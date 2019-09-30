package com.assign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assign.entity.CounterServiceType;
import com.assign.entity.Customer;
import com.assign.entity.TokenStatus;
import com.assign.exception.RecordNotFoundException;
import com.assign.service.CustomerService;
import com.assign.service.TokenService;

@RestController
@RequestMapping("/tokens")
public class TokenController {

	@Autowired
	private TokenService service;

	@Autowired
	private CustomerService customerService;

	@GetMapping("/updateTokenStatus/{tokenId}/{tokenStatus}")
	public String updateTokenStatus(@PathVariable TokenStatus tokenStatus, @PathVariable Long tokenId)
			throws RecordNotFoundException {
		service.updateTokenStatus(tokenStatus, tokenId);
		return "Updated token status Successfully";
	}

	@GetMapping("/issueToken/{customerId}/{counterServiceType}")
	public Long issueTokenToCustomer(@PathVariable Long customerId, @PathVariable CounterServiceType counterServiceType)
			throws RecordNotFoundException {
		return service.issueToken(customerService.getCustomerById(customerId), counterServiceType);
	}

	@PostMapping("/issueToken/newCustomer/{counterServiceType}")
	public Long createCustomerAndIssueToken(@RequestBody Customer customer,
			@PathVariable CounterServiceType counterServiceType) throws RecordNotFoundException {
		return service.issueToken(customerService.createCustomer(customer), counterServiceType);
	}

	@GetMapping("/changeTokenCounter/{tokenId}/{counterServiceType}")
	public Long assignCustomerToCounterServiceType(@PathVariable Long tokenId,
			@PathVariable CounterServiceType counterServiceType) throws RecordNotFoundException {
		return service.changeTokenCounter(tokenId, counterServiceType);
	}

}
