package com.assign.service;

import com.assign.entity.Customer;
import com.assign.exception.RecordNotFoundException;

public interface CustomerService {

	Customer createCustomer(Customer customer);

	Customer getCustomerById(Long id) throws RecordNotFoundException;
}
