package com.assign.service;

import com.assign.entity.CounterServiceType;
import com.assign.entity.Customer;
import com.assign.entity.ServiceType;
import com.assign.entity.Token;
import com.assign.entity.TokenStatus;
import com.assign.exception.RecordNotFoundException;

public interface TokenService {

	Token updateToken(Token token);

	Token getTokenById(Long tokenId) throws RecordNotFoundException;

	void updateTokenStatus(TokenStatus status, Long tokenId) throws RecordNotFoundException;

	Token createToken(Long customerId, ServiceType serviceType);

	Long issueToken(Customer customer, CounterServiceType counterServiceType);

	Long changeTokenCounter(Long tokenId, CounterServiceType counterServiceType) throws RecordNotFoundException;

}
