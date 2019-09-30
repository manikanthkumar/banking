package com.assign.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.assign.service.TokenService;

@Service
public class TokenServiceImpl implements TokenService {

	@Autowired
	private TokenRepository repo;

	@Autowired
	private CounterService counterService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CounterTokenMapService counterTokenMapService;

	@Override
	public Token createToken(Long customerId, ServiceType serviceType) {
		Token token = new Token();
		token.setCustomerId(customerId);
		token.setStatus(TokenStatus.active);
		token.setServiceType(serviceType);
		return repo.save(token);
	}

	@Override
	public Token updateToken(Token token) {
		return repo.save(token);
	}

	@Override
	public void updateTokenStatus(TokenStatus status, Long tokenId) throws RecordNotFoundException {
		Token token = getTokenById(tokenId);
		token.setStatus(status);
		updateToken(token);
		Long counterId = counterTokenMapService.getCounterIdFromTokenId(tokenId);
		counterTokenMapService.deleteMapByTokenId(tokenId);
		if (counterId != null) {
			List<CounterTokenMap> counterTokenMap = counterTokenMapService.getByCounterId(counterId);
			Counter counter = counterService.getCounterById(counterId);
			if (!counterTokenMap.isEmpty()) {
				counter.setPresentToken(counterTokenMap.get(0).getTokenId());
			} else {
				counter.setPresentToken(null);
			}
			counterService.updateCounter(counter);
		}
	}

	@Override
	public Token getTokenById(Long tokenId) throws RecordNotFoundException {
		Optional<Token> token = repo.findById(tokenId);
		if (token.isPresent()) {
			return token.get();
		}
		throw new RecordNotFoundException("No records found with given tokenId");
	}

	@Override
	public Long issueToken(Customer customer, CounterServiceType counterServiceType) {
		Token token = createToken(customer.getId(), customer.getServiceType());
		assignTokenToCounter(customer, counterServiceType, token.getTokenId());
		return token.getTokenId();
	}

	@Override
	public Long changeTokenCounter(Long tokenId, CounterServiceType counterServiceType) throws RecordNotFoundException {
		Token token = getTokenById(tokenId);
		fetchNextTokenForPresentCounterId(tokenId);
		return assignTokenToCounter(customerService.getCustomerById(token.getCustomerId()), counterServiceType,
				tokenId);
	}

	private Long assignTokenToCounter(Customer customer, CounterServiceType counterServiceType, Long tokenId) {
		List<Counter> counters = counterService.getCounterByServiceAndCounterServiceType(customer.getServiceType(),
				counterServiceType);
		if (counters.isEmpty()) {
			throw new RuntimeException("Counters are Not Configured");
		}
		List<CounterTokenMap> maps = counterTokenMapService.getAll();
		Long leastMaps = null;
		Long counterIdToSet = counters.get(0).getCounterId();
		for (Counter counter : counters) {
			Long count = maps.parallelStream().filter(map -> map.getCounterId().equals(counter.getCounterId())).count();
			if (leastMaps == null) {
				leastMaps = count;
			}
			if (count <= leastMaps) {
				leastMaps = count;
				counterIdToSet = counter.getCounterId();
			}
		}
		Long x = counterIdToSet;
		Counter counterToSet = counters.parallelStream().filter(counter -> counter.getCounterId().equals(x)).findFirst()
				.get();
		if (counterToSet.getPresentToken() == null) {
			counterToSet.setPresentToken(tokenId);
			counterService.updateCounter(counterToSet);
		}
		counterTokenMapService.createMap(new CounterTokenMap(tokenId, counterIdToSet));
		return counterIdToSet;
	}

	private void fetchNextTokenForPresentCounterId(Long tokenId) throws RecordNotFoundException {
		Long counterId = counterTokenMapService.getCounterIdFromTokenId(tokenId);
		counterTokenMapService.deleteMapByTokenId(tokenId);
		List<CounterTokenMap> maps = counterTokenMapService.getByCounterId(counterId);
		Counter counter = counterService.getCounterById(counterId);
		if (maps.isEmpty()) {
			counter.setPresentToken(null);
		} else {
			counter.setPresentToken(maps.get(0).getTokenId());
		}
		counterService.updateCounter(counter);

	}
}
