package com.assign.service;

import java.util.List;

import com.assign.entity.Counter;
import com.assign.entity.ServiceType;

public interface CounterService {

	Counter createCounter(Counter counter);

	Counter getCounterById(Counter counter);

	List<Counter> getAllCounters();

	List<Counter> getCounterByService(ServiceType serviceType);

	Counter updateCounter(Counter counter);
}
