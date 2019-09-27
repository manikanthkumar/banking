package com.assign.service;

import com.assign.entity.Token;

public interface TokenService {

	Token issueToken(Long customerId);
	Token updateToken(Token token);
}
