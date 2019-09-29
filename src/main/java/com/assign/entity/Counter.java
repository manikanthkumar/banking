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

	public Counter() {
		super();
	}

	public Counter(Long counterId, String counterName, Long presentToken, ServiceType serviceType,
			CounterServiceType counterService) {
		super();
		this.counterId = counterId;
		this.counterName = counterName;
		this.presentToken = presentToken;
		this.serviceType = serviceType;
		this.counterService = counterService;
	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((counterId == null) ? 0 : counterId.hashCode());
		result = prime * result + ((counterName == null) ? 0 : counterName.hashCode());
		result = prime * result + ((counterService == null) ? 0 : counterService.hashCode());
		result = prime * result + ((presentToken == null) ? 0 : presentToken.hashCode());
		result = prime * result + ((serviceType == null) ? 0 : serviceType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Counter other = (Counter) obj;
		if (counterId == null) {
			if (other.counterId != null)
				return false;
		} else if (!counterId.equals(other.counterId))
			return false;
		if (counterName == null) {
			if (other.counterName != null)
				return false;
		} else if (!counterName.equals(other.counterName))
			return false;
		if (counterService != other.counterService)
			return false;
		if (presentToken == null) {
			if (other.presentToken != null)
				return false;
		} else if (!presentToken.equals(other.presentToken))
			return false;
		if (serviceType != other.serviceType)
			return false;
		return true;
	}

}
