package com.assign.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assign.entity.CounterTokenMap;
import java.lang.Long;
import java.util.List;

@Repository
public interface CounterTokenMapRepository extends JpaRepository<CounterTokenMap, Long> {

	List<CounterTokenMap> findByCounterId(Long counterid);
}
