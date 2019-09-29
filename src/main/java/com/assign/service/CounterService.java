package com.assign.service;

import java.util.List;

import com.assign.entity.Counter;
import com.assign.entity.CounterServiceType;
import com.assign.entity.ServiceType;
import com.assign.exception.RecordNotFoundException;

public interface CounterService {

	Counter createCounter(Counter counter);

	Counter getCounterById(Long counterId) throws RecordNotFoundException;

	List<Counter> getAllCounters();

	List<Counter> getCounterByService(ServiceType serviceType);

	List<Counter> getCounterByServiceAndCounterServiceType(ServiceType serviceType,
			CounterServiceType counterServiceType);

	Counter updateCounter(Counter counter);
}
