package com.assign.service;

import java.util.List;
import java.util.Optional;

import com.assign.entity.CounterTokenMap;

public interface CounterTokenMapService {
	CounterTokenMap createMap(CounterTokenMap map);
	CounterTokenMap updateMap(CounterTokenMap map);
	List<CounterTokenMap> getByCounterId(Long counterId);
	List<CounterTokenMap> getAll();
	String deleteMapByTokenId(Long tokenId);
	Long getCounterIdFromTokenId(Long tokenId);
	Optional<CounterTokenMap> getByTokenId(Long tokenId);
}
