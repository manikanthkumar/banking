package com.assign.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assign.entity.CounterTokenMap;
import com.assign.repository.CounterTokenMapRepository;
import com.assign.service.CounterTokenMapService;

@Service
public class CounterTokenMapServiceImpl implements CounterTokenMapService {

	@Autowired
	private CounterTokenMapRepository repo;

	@Override
	public CounterTokenMap createMap(CounterTokenMap map) {
		return repo.save(map);
	}

	@Override
	public CounterTokenMap updateMap(CounterTokenMap map) {
		return repo.save(map);
	}

	@Override
	public String deleteMapByTokenId(Long tokenId) {
		repo.deleteById(tokenId);
		return "Deleted Successfully";
	}

	@Override
	public List<CounterTokenMap> getByCounterId(Long counterId) {
		return repo.findByCounterId(counterId).parallelStream()
				.sorted(Comparator.comparing(CounterTokenMap::getTokenId)).collect(Collectors.toList());
	}

	@Override
	public Long getCounterIdFromTokenId(Long tokenId) {
		Optional<CounterTokenMap> map = getByTokenId(tokenId);
		if (map.isPresent()) {
			return map.get().getCounterId();
		}
		return null;
	}

	@Override
	public Optional<CounterTokenMap> getByTokenId(Long tokenId) {
		return repo.findById(tokenId);
	}

	@Override
	public List<CounterTokenMap> getAll() {
		return repo.findAll();
	}

}
