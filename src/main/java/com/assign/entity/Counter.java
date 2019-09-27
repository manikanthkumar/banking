package com.assign.entity;

public class Counter {
	int counterId;
	String counterName;
	Long presentToken;
	ServiceType serviceType;

	public int getCounterId() {
		return counterId;
	}

	public void setCounterId(int counterId) {
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
