package com.assign.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Tokens")
public class Token {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long tokenId;
	TokenStatus status;
	String comments;
	String actionItem;
	Long customerId;
	ServiceType serviceType;
	CounterServiceType counterService;

	public Token() {
		super();
	}

	public Token(Long tokenId, TokenStatus status, String comments, String actionItem, Long customerId,
			ServiceType serviceType, CounterServiceType counterService) {
		super();
		this.tokenId = tokenId;
		this.status = status;
		this.comments = comments;
		this.actionItem = actionItem;
		this.customerId = customerId;
		this.serviceType = serviceType;
		this.counterService = counterService;
	}

	public CounterServiceType getCounterService() {
		return counterService;
	}

	public void setCounterService(CounterServiceType counterService) {
		this.counterService = counterService;
	}

	public Long getTokenId() {
		return tokenId;
	}

	public void setTokenId(Long tokenId) {
		this.tokenId = tokenId;
	}

	public TokenStatus getStatus() {
		return status;
	}

	public void setStatus(TokenStatus status) {
		this.status = status;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getActionItem() {
		return actionItem;
	}

	public void setActionItem(String actionItem) {
		this.actionItem = actionItem;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public ServiceType getServiceType() {
		return serviceType;
	}

	public void setServiceType(ServiceType serviceType) {
		this.serviceType = serviceType;
	}

	/*
	 * public List<Service> getServices() { return services; }
	 * 
	 * public void setServices(List<Service> services) { this.services = services; }
	 */

}
