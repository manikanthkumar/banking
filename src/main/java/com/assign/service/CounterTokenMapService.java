package com.assign.service;

import com.assign.entity.CounterTokenMap;

public interface CounterTokenMapService {
	CounterTokenMap createMap(CounterTokenMap map);
	CounterTokenMap updateMap(CounterTokenMap map);
	String deleteMapByTokenId(Long tokenId);
}
