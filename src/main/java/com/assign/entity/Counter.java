package com.assign.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Counters")
public class Counter {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long counterId;
	String counterName;
	Long presentToken;
	ServiceType serviceType;
	CounterServiceType counterService;

	public CounterServiceType getCounterService() {
		return counterService;
	}

	public void setCounterService(CounterServiceType counterService) {
		this.counterService = counterService;
	}

	public Long getCounterId() {
		return counterId;
	}

	public void setCounterId(Long counterId) {
		this.counterId = counterId;
	}

	public String getCounterName() {
		return counterName;
	}

	public void setCounterName(String counterName) {
		this.counterName = counterName;
	}

	public Long getPresentToken() {
		return presentToken;
	}

	public void setPresentToken(Long presentToken) {
		this.presentToken = presentToken;
	}

	public ServiceType getServiceType() {
		return serviceType;
	}

	public void setServiceType(ServiceType serviceType) {
		this.serviceType = serviceType;
	}

}
