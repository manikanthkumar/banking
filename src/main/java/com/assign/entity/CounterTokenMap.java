package com.assign.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CounterTokenMappings")
public class CounterTokenMap {
	@Id
	Long tokenId;
	Long counterId;

	public CounterTokenMap() {
		super();
	}

	public CounterTokenMap(Long tokenId, Long counterId) {
		super();
		this.tokenId = tokenId;
		this.counterId = counterId;
	}

	public Long getTokenId() {
		return tokenId;
	}

	public void setTokenId(Long tokenId) {
		this.tokenId = tokenId;
	}

	public Long getCounterId() {
		return counterId;
	}

	public void setCounterId(Long counterId) {
		this.counterId = counterId;
	}

}
