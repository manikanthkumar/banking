package com.assign.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assign.entity.Counter;
import com.assign.entity.CounterServiceType;
import com.assign.entity.ServiceType;
import com.assign.exception.RecordNotFoundException;
import com.assign.repository.CounterRepository;
import com.assign.service.CounterService;

@Service
public class CounterServiceImpl implements CounterService {

	@Autowired
	private CounterRepository repo;

	@Override
	public Counter createCounter(Counter counter) {
		return repo.save(counter);
	}

	@Override
	public Counter getCounterById(Long counterId) throws RecordNotFoundException {
		Optional<Counter> counter = repo.findById(counterId);
		if (counter.isPresent()) {
			return counter.get();
		}
		throw new RecordNotFoundException("No counters found with given counterId");
	}

	@Override
	public List<Counter> getAllCounters() {
		return repo.findAll();
	}

	@Override
	public List<Counter> getCounterByService(ServiceType serviceType) {
		return repo.findByServiceType(serviceType);
	}

	@Override
	public Counter updateCounter(Counter counter) {
		return repo.save(counter);
	}

	@Override
	public List<Counter> getCounterByServiceAndCounterServiceType(ServiceType serviceType,
			CounterServiceType counterServiceType) {
		return repo.findByServiceTypeAndCounterService(serviceType, counterServiceType);
	}

}
